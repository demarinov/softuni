"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var home_component_1 = require("./home/home.component");
var page_not_found_component_1 = require("./page-not-found/page-not-found.component");
var router_1 = require("@angular/router");
var PagesModule = /** @class */ (function () {
    function PagesModule() {
    }
    PagesModule = __decorate([
        core_1.NgModule({
            declarations: [
                home_component_1.HomeComponent,
                page_not_found_component_1.PageNotFoundComponent
            ],
            imports: [
                common_1.CommonModule,
                router_1.RouterModule
            ],
            exports: [home_component_1.HomeComponent]
        })
    ], PagesModule);
    return PagesModule;
}());
exports.PagesModule = PagesModule;

//# sourceMappingURL=pages.module.js.map
