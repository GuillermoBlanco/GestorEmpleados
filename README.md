GestorEmpleados
===============

Requerimientos

1. Funcionales
La parte del SERVIDOR se ejecutará en un servidor Apache (se ha utilizado XAMP v1.8.1),
sobre un sistema operativo Windows 7 y requiere tener instalado una Máquina Virtual de
Java.
Este servidor albergará el sistema gestor de la base de datos, que será MySQL y estará
administrado con MySQL Workbench v5.2.
La parte CLIENTE estará implementada en un equipo Windows también con el
requerimiento de una Máquina Virtual de Java.
En cuanto a hardware mínimo:
Una máquina con 512 MB de RAM y un espacio en disco de 256MB.
Para una configuración cliente servidor sobre una red LAN el requerimiento para ambas
máquinas es el mismo.

2. No funcionales
Para generar los diagramas en la fase de diseño se ha dispuesto de la herramienta Dia, por
ser de libre distribución. La planificación se ha llevado acabo con la aplicación
GanttProject. La documentació y los diagramas de clases han sido generados con Doxygen
y los modelos de entidad-relación con MySQL Workbench v5.2
La aplicación tanto en la parte servidor como en la parte cliente, ha sido desarrollada sobre
la api de Java SE v1.6 con el IDE de desarrollo Eclipse en su versión Indigo, se ha optado
por el uso de distintas API`s externas, Hibernate como DAO, Jasperreports para la
generación de reportes Jexcelapi para la exportación en hojas de cálculo.
Las plantillas que se usarán para los diferentes reportes se realizaron con la herramienta
iReport.
