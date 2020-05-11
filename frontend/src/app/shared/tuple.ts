export class Tuple {

    constructor(
        public x: number,
        public y: number
        ) {}

    equals( x: number, y: number) {
        return this.x === x && this.y === y;
    }

    equalsT( tuple: Tuple) {
        return this.x === tuple.x && this.y === tuple.y;
    }
}
