package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.BankingDto;
import com.dido.holidaybay.model.dto.DepositDto;
import com.dido.holidaybay.model.dto.WithdrawDto;
import com.dido.holidaybay.model.entity.BankAccount;
import com.dido.holidaybay.model.entity.UserEntity;
import com.dido.holidaybay.service.BankingService;
import com.dido.holidaybay.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/banking")
public class BankingController {

    private final UserService userService;
    private final BankingService bankingService;
    private final ObjectMapper objectMapper;

    @GetMapping("")
    public String cashier(Principal principal, Model model) {

        if (model.getAttribute("depositModel") == null) {
            model.addAttribute("depositModel", DepositDto.builder().build());
        }

        if (model.getAttribute("withdrawModel") == null) {
            model.addAttribute("withdrawModel", WithdrawDto.builder().build());
        }

        UserEntity user = userService.getUserByUserName(principal.getName());
        BankAccount bankAccount = bankingService.getBankAccount(user);

        model.addAttribute("funds", bankAccount.getAmount());

        return "cashier";
    }

    @GetMapping("/funds")
    public @ResponseBody String getFunds(Principal principal) throws JsonProcessingException {

        UserEntity user = userService.getUserByUserName(principal.getName());
        BankAccount bankAccount = bankingService.getBankAccount(user);

        return bankAccount != null ? objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(BankingDto.builder()
                        .depositAmount(bankAccount.getAmount())
                        .build()) :
                objectMapper.writeValueAsString(BankingDto.builder().build());
    }

    @PostMapping("/deposit")
    public String deposit(@Valid DepositDto bankingDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model,
                          Principal principal) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("depositModel", bankingDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.depositModel", bindingResult);
            return "redirect:/banking";
        }

        UserEntity user = userService.getUserByUserName(principal.getName());

        bankingService.deposit(bankingDto.getDepositAmount(), user);

        redirectAttributes.addFlashAttribute("funds", bankingService.getBankAccount(user).getAmount());

        return "redirect:/banking";
    }


    @PostMapping("/withdraw")
    public String withdraw(Principal principal, @Valid WithdrawDto bankingDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("withdrawModel", bankingDto);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.withdrawModel", bindingResult);

            return "redirect:/banking";
        }

        UserEntity user = userService.getUserByUserName(principal.getName());
        boolean result = bankingService.withdraw(bankingDto.getWithdrawAmount(), user);

        if (!result) {
            redirectAttributes.addFlashAttribute("not_enough_money", true);
        }

        redirectAttributes.addFlashAttribute("funds", bankingService.getBankAccount(user).getAmount());

        return "redirect:/banking";
    }

}
