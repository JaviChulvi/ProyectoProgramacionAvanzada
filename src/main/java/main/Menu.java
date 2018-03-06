package main;

public enum Menu {
    //Dar de alta un nuevo cliente.
    //Borrar un cliente.
    //Cambiar la tarifa de un cliente.
    //Recuperar los datos de un cliente a partir de su NIF.
    //Recuperar el listado de todos los clientes.
    //
    //Dar de alta una llamada.
    //Listar todas las llamadas de un cliente.
    //
    //Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas.
    //Recuperar los datos de una factura a partir de su código.
    //Recuperar todas las facturas de un cliente



    ALTA_NUEVO_CLIENTE("Dar de alta un nuevo cliente."),
    BORRAR_CLIENTE("Borrar un cliente."),
    CAMBIAR_TARIFA("Cambiar la tarifa de un cliente."),
    DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF."),
    LISTADO_CLIENTE("Recuperar el listado de todos los clientes."),
    ALTA_LLAMADA("Dar de alta una llamada."),
    LISTAR_LLAMADAS_CLIENTE("Listar todas las llamadas de un cliente."),
    EMITIR_FACTURAS_CLIENTE("Emitir una factura para un cliente, calculando el importe de la misma en función de las llamadas."),
    DATOS_FACTURA("Recuperar los datos de una factura a partir de su código."),
    LISTAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente.");


    private String descripcion;

    private Menu(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public static Menu getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(Menu opcion: Menu.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }


}
