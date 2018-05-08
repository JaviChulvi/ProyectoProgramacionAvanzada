package controlador;

public enum MenuConsolaEnum {
    ALTA_NUEVO_CLIENTE("Dar de alta un nuevo cliente."),
    BORRAR_CLIENTE("Borrar un cliente."),
    APLICAR_OFERTA("Aplica una oferta"),
    DATOS_CLIENTE("Recuperar los datos de un cliente a partir de su NIF."),
    LISTADO_CLIENTES("Recuperar el listado de todos los modelo.clientes."),
    ALTA_LLAMADA("Dar de alta una llamada."),
    LISTAR_LLAMADAS_CLIENTE("Listar todas las modelo.llamadas de un cliente."),
    EMITIR_FACTURAS_CLIENTE("Emitir una factura para un cliente, calculando el importe de la misma en función de las modelo.llamadas."),
    DATOS_FACTURA("Recuperar los datos de una factura a partir de su código."),
    LISTAR_FACTURAS_CLIENTE("Recuperar todas las modelo.facturas de un cliente."),
    ALTA_CLIENTES_ENTRE_DOS_FECHAS("Mostrar un listado de modelo.clientes que fueron dados de alta entre dos fechas."),
    LLAMADAS_ENTRE_DOS_FECHAS("Mostrar un listado de modelo.llamadas de un cliente que fueron realizadas entre dos fechas."),
    FACTURAS_ENTRE_DOS_FECHAS("Mostrar un listado de modelo.facturas de un cliente emitidas entre dos fechas."),
    SALIR("Salir.");

    private String descripcion;

    private MenuConsolaEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static MenuConsolaEnum getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(MenuConsolaEnum opcion: MenuConsolaEnum.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }

}
