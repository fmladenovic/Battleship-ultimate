import { Player } from './player';
import { Ship } from './ship';
import { Move } from './move';
import { Ships } from './ships';


export class Game {

    id: string;
    player: Player;

    playerMoves: Move[]; 
    computerShips: Ships;

    computerMoves: Move[]; 
    playerShips: Ships;

    constructor() {
        this.playerMoves = []; 
        this.computerMoves = []; 
    }
}
