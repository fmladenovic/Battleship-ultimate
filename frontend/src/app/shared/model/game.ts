import { Player } from './player';
import { Ship } from './ship';
import { Move } from './move';


export class Game {

    id: string;
    player: Player;

    playerMoves: Move[]; 
    computerShips: Ship[];

    computerMoves: Move[]; 
    playerShips: Ship[];

    constructor() {
        this.playerMoves = []; 
        this.computerShips = [];
        this.computerMoves = []; 
        this.playerShips = []; 
    }
}
