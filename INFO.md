# ¿QUE ES ESTE ARCHIVO?
Voy a utilizar este archivo para documentar los mecanismos de como implementar el tetris

## Cosas a tener en cuenta

---
- Para la logica del tablero de teris hay una clase (TetrisGrid) que maneja toda la lógica del tablero incluyendo:
  - Comprobar si una fila esta vacia o llena
  - Limpiar una fila
  - Comrpobar las filas que esten llenas, vaciarlas y mover el resto de filas hacia abajo
  - Establecer una celda de un color determinado (esto sirve para "pegar" el tetranomino al board)
 
## TO-DO
- [ ] Hacer la clase que gestione la logica de los Tetranominos:
  - [ ] Posicion en el GRID; utilizando unas variables para establecer su posicion en el board (offset)
  - [ ] Metodos para mover el tetranomino de posicion, haciendo comprobaciones para que no se pueda salir del board; esto no etoy seguro si hacerlo en un archivo a parte que gestione todos los movimientos e inputs del usuario o incluirlo en la clase principal del tetranomino.
  - [ ] Implementar el SRS; Es el sistema oficial que usa  tetris para los movimientos, es considerablemente mas "facil" que la alternativa de calcualr la rotacion de la matriz

## Los Tetrominos
- Las piezas tendran "estados" que son basicamente matrices con sus diferentes estados de rotacion
- Cada pieza tiene que tener variables que definan su ubicacion inicial al ser creadas o reinciadas (para reutilizar recursos), cada pieza empieza en el estado "0" y dependiendo del tipo tienen un tamaño que debe de ser ajustado.
- Cada pieza tiene dos variables que indican su posicion X e Y en el board, 