# grafos_algoritmos

## Descripcion
Algoritmos para Grafos dirigidos y no dirigidos hecho en Java.

Los algoritmos fueron desarrollados en las clases de Programacion Avanzada en la UNLaM.

Contiene las clases arista, nodo, grafo ponderado y grafo no ponderado con los algoritmos de dijkstra, floyd-warshall y kruscal.

## Ejecucion
- Se necesita JRE de java para poder ejecutarlo.
- Se puede ejecutar desde (se puede cambiar el archivo de alguno de los tres grafos de prueba dentro del codigo):
  grafos_algoritmos/src/desarrollo/GrafoPD.java
  grafos_algoritmos/src/desarrollo/GrafoPND.java

## Testing
El grafo se construye a partir de archivos .in, en la base de este repositorio.
Los archivos de prueba estan conformados por una primera linea que la conforman la cantidad de nodos y aristas, y cada linea la conforma una
arista que indica el nodo origen, nodo destino y peso en caso de los grafos ponderados.

## Ejemplo

 3 3
 
 1 2 1
 
 3 1 3
 
 2 3 2
