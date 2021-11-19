# Chess

### Functionality

Thus far this Java chess application consists of a GUI that's nothing more than a chessboard with chess pieces on it.
Both players can play on the same machine by clicking on a piece they want to move and then clicking where they want to move it.
The application won't allow players to make invalid moves.

### Classes

#### application Package
The `Application` class contains `main` which creates a new `ChessMatch` object as well as an `Interaction` object which
serves as the application's GUI.
#### interaction Package
The `Interaction` object serves as the application's GUI, listens for clicks and calls `chessMatch`'s `move` method.
#### matchcomponents Package
A `Board` wraps a `Piece[][]` for which a `null` value represents an empty square.
###### matchcomponents.move
A `MoveAttempt` consists of two file-rank pairs and its `isValid` method is called to determine if the pairs represent a valid move.
The main framework used to determine whether a move is valid involves the use of `Path`s—sequences of `Displacement`s with respect
to a piece's position at the time a move is attempted.
###### matchcomponents.piece
A `Piece` is merely a `PieceType` enumeration value combined with an `isWhite` boolean.
The `PieceType` enum stores `Set<Path>`s for pieces which are setup via the `setupPathSets` method which is called in `main`.
###### matchcomponents.player
`HumanPlayer` overrides `Player`'s `getMove` method—which returns a `MoveAttempt`—by talking to the `Interaction`.
