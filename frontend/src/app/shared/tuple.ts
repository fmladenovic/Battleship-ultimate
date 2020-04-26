export class Tuple {

    constructor(
        public i: number,
        public j: number
        ) {}

    equals( i: number, j: number) {
        return this.i === i && this.j === j;
    }

    equalsT( tuple: Tuple) {
        return this.i === tuple.i && this.j === tuple.j;
    }
}
