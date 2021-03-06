function solution() {

    class Balloon {

        constructor(color, hasWeight) {
            this.color = color;
            this.hasWeight = Number(hasWeight);
        }
    }

    class PartyBalloon extends Balloon {
        constructor(color, hasWeight, ribbonColor, ribbonLength) {
            super(color, hasWeight);
            this._ribbon = {
                color: ribbonColor,
                length: Number(ribbonLength),
            };

        }

        get ribbon() {
            return this._ribbon;
        }
    }

    class BirthdayBalloon extends PartyBalloon {

        constructor(color, hasWeight, ribbonColor, ribbonLength, text) {
            super(color, hasWeight, ribbonColor, ribbonLength);
            this._text = text;
        }

        get text() {
            return this._text;
        }
    }

    return {
        Balloon,
        PartyBalloon,
        BirthdayBalloon
    };
}

function test() {
    let classes = solution();
    let testBalloon = new classes.Balloon("Tumno-bqlo", 20.5);
    let testPartyBalloon = new classes.PartyBalloon("Tumno-bqlo", 20.5, "Svetlo-cherno", 10.25);
    let ribbon = testPartyBalloon.ribbon;
    console.log(testBalloon);
    console.log(testPartyBalloon);
    console.log(ribbon);

}

test();