package ar.com.juliospa.edu.dmkd.cuat1.dmf.automatizar.arbol.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;

public abstract class CamposSQLHelper {
	
	public static List<String> getCamposVersionConHistoyVars() {
		List<String> list = new ArrayList<String>();
		list.add("clase [n] ");
		list.add("numero_de_cliente [s] ");
		list.add("foto_mes [s] ");
		list.add("marketing_activo_ultimos90dias [n] ");
		list.add("cliente_vip [n] ");
		list.add("cliente_sucursal [s] ");
		list.add("cliente_edad [s] ");
		list.add("cliente_antiguedad [s] ");
		list.add("mrentabilidad [s] ");
		list.add("mrentabilidad_annual [n] ");
		list.add("mcomisiones [s] ");
		list.add("mactivos_margen [s] ");
		list.add("mpasivos_margen [s] ");
		list.add("marketing_coss_selling [n] ");
		list.add("tpaquete1 [n] ");
		list.add("tpaquete2 [n] ");
		list.add("tpaquete3 [n] ");
		list.add("tpaquete4 [n] ");
		list.add("tpaquete5 [n] ");
		list.add("tpaquete6 [n] ");
		list.add("tpaquete7 [n] ");
		list.add("tpaquete8 [n] ");
		list.add("tpaquete9 [n] ");
		list.add("tcuentas [n] ");
		list.add("tcuenta_corriente [n] ");
		list.add("mcuenta_corriente_Nopaquete [s] ");
		list.add("mcuenta_corriente_Paquete [s] ");
		list.add("mcuenta_corriente_dolares [n] ");
		list.add("tcaja_ahorro [n] ");
		list.add("mcaja_ahorro_Paquete [s] ");
		list.add("mcaja_ahorro_Nopaquete [s] ");
		list.add("mcaja_ahorro_dolares [s] ");
		list.add("mdescubierto_preacordado [s] ");
		list.add("mcuentas_saldo [s] ");
		list.add("ttarjeta_debito [n] ");
		list.add("ctarjeta_debito_transacciones [s] ");
		list.add("mautoservicio [s] ");
		list.add("ttarjeta_visa [n] ");
		list.add("ctarjeta_visa_transacciones [s] ");
		list.add("mtarjeta_visa_consumo [s] ");
		list.add("ttarjeta_master [n] ");
		list.add("ctarjeta_master_transacciones [s] ");
		list.add("mtarjeta_master_consumo [s] ");
		list.add("cprestamos_personales [s] ");
		list.add("mprestamos_personales [s] ");
		list.add("cprestamos_prendarios [n] ");
		list.add("mprestamos_prendarios [s] ");
		list.add("cprestamos_hipotecarios [n] ");
		list.add("mprestamos_hipotecarios [s] ");
		list.add("tplazo_fijo [n] ");
		list.add("mplazo_fijo_dolares [s] ");
		list.add("mplazo_fijo_pesos [s] ");
		list.add("tfondos_comunes_inversion [n] ");
		list.add("mfondos_comunes_inversion_pesos [s] ");
		list.add("mfondos_comunes_inversion_dolares [s] ");
		list.add("ttitulos [n] ");
		list.add("mtitulos [s] ");
		list.add("tseguro_vida_mercado_abierto [n] ");
		list.add("tseguro_auto [n] ");
		list.add("tseguro_vivienda [n] ");
		list.add("tseguro_accidentes_personales [n] ");
		list.add("tcaja_seguridad [n] ");
		list.add("mbonos_gobierno [n] ");
		list.add("mmonedas_extranjeras [n] ");
		list.add("minversiones_otras [n] ");
		list.add("tplan_sueldo [n] ");
		list.add("mplan_sueldo [s] ");
		list.add("mplan_sueldo_manual [s] ");
		list.add("cplan_sueldo_transaccion [n] ");
		list.add("tcuenta_debitos_automaticos [n] ");
		list.add("mcuenta_debitos_automaticos [s] ");
		list.add("ttarjeta_visa_debitos_automaticos [n] ");
		list.add("mttarjeta_visa_debitos_automaticos [s] ");
		list.add("ttarjeta_master_debitos_automaticos [n] ");
		list.add("mttarjeta_master_debitos_automaticos [s] ");
		list.add("tpagodeservicios [n] ");
		list.add("mpagodeservicios [s] ");
		list.add("tpagomiscuentas [n] ");
		list.add("mpagomiscuentas [s] ");
		list.add("ccajeros_propios_descuentos [n] ");
		list.add("mcajeros_propios_descuentos [s] ");
		list.add("ctarjeta_visa_descuentos [s] ");
		list.add("mtarjeta_visa_descuentos [s] ");
		list.add("ctarjeta_master_descuentos [s] ");
		list.add("mtarjeta_master_descuentos [s] ");
		list.add("ccuenta_descuentos [n] ");
		list.add("mcuenta_descuentos [n] ");
		list.add("ccomisiones_mantenimiento [s] ");
		list.add("mcomisiones_mantenimiento [s] ");
		list.add("ccomisiones_otras [s] ");
		list.add("mcomisiones_otras [s] ");
		list.add("tcambio_monedas [n] ");
		list.add("ccambio_monedas_compra [n] ");
		list.add("mcambio_monedas_compra [s] ");
		list.add("ccambio_monedas_venta [n] ");
		list.add("mcambio_monedas_venta [s] ");
		list.add("ctransferencias_recibidas [n] ");
		list.add("mtransferencias_recibidas [s] ");
		list.add("ctransferencias_emitidas [s] ");
		list.add("mtransferencias_emitidas [s] ");
		list.add("cextraccion_autoservicio [s] ");
		list.add("mextraccion_autoservicio [s] ");
		list.add("ccheques_depositados [s] ");
		list.add("mcheques_depositados [s] ");
		list.add("ccheques_emitidos [s] ");
		list.add("mcheques_emitidos [s] ");
		list.add("ccheques_depositados_rechazados [n] ");
		list.add("mcheques_depositados_rechazados [s] ");
		list.add("ccheques_emitidos_rechazados [s] ");
		list.add("mcheques_emitidos_rechazados [s] ");
		list.add("tcallcenter [n] ");
		list.add("ccallcenter_transacciones [s] ");
		list.add("thomebanking [n] ");
		list.add("chomebanking_transacciones [s] ");
		list.add("tautoservicio [n] ");
		list.add("cautoservicio_transacciones [n] ");
		list.add("tcajas [n] ");
		list.add("tcajas_consultas [n] ");
		list.add("tcajas_depositos [n] ");
		list.add("tcajas_extracciones [n] ");
		list.add("tcajas_otras [n] ");
		list.add("ccajeros_propio_transacciones [s] ");
		list.add("mcajeros_propio [s] ");
		list.add("ccajeros_ajenos_transacciones [s] ");
		list.add("mcajeros_ajenos [s] ");
		list.add("tmovimientos_ultimos90dias [n] ");
		list.add("Master_marca_atraso [n] ");
		list.add("Master_cuenta_estado [s] ");
		list.add("Master_mfinanciacion_limite [s] ");
		list.add("Master_Fvencimiento [s] ");
		list.add("Master_Finiciomora [s] ");
		list.add("Master_msaldototal [s] ");
		list.add("Master_msaldopesos [s] ");
		list.add("Master_msaldodolares [s] ");
		list.add("Master_mconsumospesos [s] ");
		list.add("Master_mconsumosdolares [s] ");
		list.add("Master_mlimitecompra [s] ");
		list.add("Master_madelantopesos [s] ");
		list.add("Master_madelantodolares [s] ");
		list.add("Master_fultimo_cierre [s] ");
		list.add("Master_mpagado [s] ");
		list.add("Master_mpagospesos [s] ");
		list.add("Master_mpagosdolares [s] ");
		list.add("Master_fechaalta [s] ");
		list.add("Master_mconsumototal [s] ");
		list.add("Master_tconsumos [n] ");
		list.add("Master_tadelantosefectivo [n] ");
		list.add("Master_mpagominimo [s] ");
		list.add("Visa_marca_atraso [n] ");
		list.add("Visa_cuenta_estado [s] ");
		list.add("Visa_mfinanciacion_limite [s] ");
		list.add("Visa_Fvencimiento [s] ");
		list.add("Visa_Finiciomora [s] ");
		list.add("Visa_msaldototal [s] ");
		list.add("Visa_msaldopesos [s] ");
		list.add("Visa_msaldodolares [s] ");
		list.add("Visa_mconsumospesos [s] ");
		list.add("Visa_mconsumosdolares [s] ");
		list.add("Visa_mlimitecompra [s] ");
		list.add("Visa_madelantopesos [s] ");
		list.add("Visa_madelantodolares [s] ");
		list.add("Visa_fultimo_cierre [s] ");
		list.add("Visa_mpagado [s] ");
		list.add("Visa_mpagospesos [s] ");
		list.add("Visa_mpagosdolares [s] ");
		list.add("Visa_fechaalta [s] ");
		list.add("Visa_mconsumototal [s] ");
		list.add("Visa_tconsumos [n] ");
		list.add("Visa_tadelantosefectivo [n] ");
		list.add("Visa_mpagominimo [s] ");
		list.add("participa [n] ");
		list.add("jestado_seguros [n] ");
		list.add("jestado_debitos [n] ");
		list.add("jestado_contacto [n] ");
		list.add("jestado_visa_dias_mora_relativa_periodo [s] ");
		list.add("jestado_master_dias_mora_relativa_periodo [s] ");
		list.add("jestado_visa_ratio_consumo_limite [s] ");
		list.add("jestado_master_ratio_consumo_limite [s] ");
		list.add("jestado_visa_ratio_minimo_consumo_limite [s] ");
		list.add("jestado_master_ratio_minimo_consumo_limite [s] ");
		list.add("tarjeta_marca_atraso [s] ");
		list.add("tarjeta_cuenta_estado [s] ");
		list.add("tarjeta_mfinanciacion_limite [s] ");
		list.add("tarjeta_Fvencimiento [n] ");
		list.add("tarjeta_Finiciomora [n] ");
		list.add("jestado_tarjeta_dias_mora_relativa_periodo [s] ");
		list.add("jestado_tarjeta_ratio_consumo_limite [s] ");
		list.add("jestado_tarjeta_ratio_minimo_consumo_limite [s] ");
		list.add("tarjeta_msaldototal [s] ");
		list.add("tarjeta_msaldopesos [s] ");
		list.add("tarjeta_msaldodolares [s] ");
		list.add("tarjeta_mconsumospesos [s] ");
		list.add("tarjeta_mconsumosdolares [s] ");
		list.add("tarjeta_mlimitecompra [s] ");
		list.add("tarjeta_madelantopesos [s] ");
		list.add("tarjeta_madelantodolares [s] ");
		list.add("tarjeta_fultimo_cierre [n] ");
		list.add("tarjeta_mpagado [s] ");
		list.add("tarjeta_mpagospesos [s] ");
		list.add("tarjeta_mpagosdolares [s] ");
		list.add("tarjeta_fechaalta [n] ");
		list.add("tarjeta_mconsumototal [s] ");
		list.add("tarjeta_tconsumos [n] ");
		list.add("tarjeta_tadelantosefectivo [n] ");
		list.add("tarjeta_mpagominimo [s] ");
		list.add("mrentabilidad_AVG [s] ");
		list.add("mcomisiones_AVG [s] ");
		list.add("mactivos_margen_AVG [s] ");
		list.add("mpasivos_margen_AVG [s] ");
		list.add("mcuenta_corriente_Nopaquete_AVG [s] ");
		list.add("mcuenta_corriente_Paquete_AVG [s] ");
		list.add("mcaja_ahorro_Paquete_AVG [s] ");
		list.add("mcaja_ahorro_Nopaquete_AVG [s] ");
		list.add("mcaja_ahorro_dolares_AVG [s] ");
		list.add("mdescubierto_preacordado_AVG [s] ");
		list.add("mcuentas_saldo_AVG [s] ");
		list.add("mautoservicio_AVG [s] ");
		list.add("mtarjeta_visa_consumo_AVG [s] ");
		list.add("mtarjeta_master_consumo_AVG [s] ");
		list.add("mprestamos_personales_AVG [s] ");
		list.add("mprestamos_prendarios_AVG [s] ");
		list.add("mprestamos_hipotecarios_AVG [s] ");
		list.add("mplazo_fijo_dolares_AVG [s] ");
		list.add("mplazo_fijo_pesos_AVG [s] ");
		list.add("mfondos_comunes_inversion_pesos_AVG [s] ");
		list.add("mfondos_comunes_inversion_dolares_AVG [s] ");
		list.add("mtitulos_AVG [s] ");
		list.add("mplan_sueldo_AVG [s] ");
		list.add("mplan_sueldo_manual_AVG [s] ");
		list.add("mcuenta_debitos_automaticos_AVG [s] ");
		list.add("mttarjeta_visa_debitos_automaticos_AVG [s] ");
		list.add("mttarjeta_master_debitos_automaticos_AVG [s] ");
		list.add("mpagodeservicios_AVG [s] ");
		list.add("mpagomiscuentas_AVG [s] ");
		list.add("mcajeros_propios_descuentos_AVG [s] ");
		list.add("mtarjeta_visa_descuentos_AVG [s] ");
		list.add("mtarjeta_master_descuentos_AVG [s] ");
		list.add("mcomisiones_mantenimiento_AVG [s] ");
		list.add("mcomisiones_otras_AVG [s] ");
		list.add("mcambio_monedas_compra_AVG [s] ");
		list.add("mcambio_monedas_venta_AVG [s] ");
		list.add("mtransferencias_recibidas_AVG [s] ");
		list.add("mtransferencias_emitidas_AVG [s] ");
		list.add("mextraccion_autoservicio_AVG [s] ");
		list.add("mcheques_depositados_AVG [s] ");
		list.add("mcheques_emitidos_AVG [s] ");
		list.add("mcheques_depositados_rechazados_AVG [s] ");
		list.add("mcheques_emitidos_rechazados_AVG [s] ");
		list.add("mcajeros_propio_AVG [s] ");
		list.add("mcajeros_ajenos_AVG [s] ");
		list.add("Master_mfinanciacion_limite_AVG [s] ");
		list.add("Master_msaldototal_AVG [s] ");
		list.add("Master_msaldopesos_AVG [s] ");
		list.add("Master_msaldodolares_AVG [s] ");
		list.add("Master_mconsumospesos_AVG [s] ");
		list.add("Master_mconsumosdolares_AVG [s] ");
		list.add("Master_mlimitecompra_AVG [s] ");
		list.add("Master_madelantopesos_AVG [s] ");
		list.add("Master_madelantodolares_AVG [s] ");
		list.add("Master_mpagado_AVG [s] ");
		list.add("Master_mpagospesos_AVG [s] ");
		list.add("Master_mpagosdolares_AVG [s] ");
		list.add("Master_mconsumototal_AVG [s] ");
		list.add("Master_mpagominimo_AVG [s] ");
		list.add("Visa_mfinanciacion_limite_AVG [s] ");
		list.add("Visa_msaldototal_AVG [s] ");
		list.add("Visa_msaldopesos_AVG [s] ");
		list.add("Visa_msaldodolares_AVG [s] ");
		list.add("Visa_mconsumospesos_AVG [s] ");
		list.add("Visa_mconsumosdolares_AVG [s] ");
		list.add("Visa_mlimitecompra_AVG [s] ");
		list.add("Visa_madelantopesos_AVG [s] ");
		list.add("Visa_madelantodolares_AVG [s] ");
		list.add("Visa_mpagado_AVG [s] ");
		list.add("Visa_mpagospesos_AVG [s] ");
		list.add("Visa_mpagosdolares_AVG [s] ");
		list.add("Visa_mconsumototal_AVG [s] ");
		list.add("Visa_mpagominimo_AVG [s] ");
		list.add("jestado_visa_ratio_consumo_limite_AVG [s] ");
		list.add("jestado_master_ratio_consumo_limite_AVG [s] ");
		list.add("jestado_visa_ratio_minimo_consumo_limite_AVG [s] ");
		list.add("jestado_master_ratio_minimo_consumo_limite_AVG [s] ");
		list.add("tarjeta_mfinanciacion_limite_AVG [s] ");
		list.add("jestado_tarjeta_ratio_consumo_limite_AVG [s] ");
		list.add("jestado_tarjeta_ratio_minimo_consumo_limite_AVG [s] ");
		list.add("tarjeta_msaldototal_AVG [s] ");
		list.add("tarjeta_msaldopesos_AVG [s] ");
		list.add("tarjeta_msaldodolares_AVG [s] ");
		list.add("tarjeta_mconsumospesos_AVG [s] ");
		list.add("tarjeta_mconsumosdolares_AVG [s] ");
		list.add("tarjeta_mlimitecompra_AVG [s] ");
		list.add("tarjeta_madelantopesos_AVG [s] ");
		list.add("tarjeta_madelantodolares_AVG [s] ");
		list.add("tarjeta_mpagado_AVG [s] ");
		list.add("tarjeta_mpagospesos_AVG [s] ");
		list.add("tarjeta_mpagosdolares_AVG [s] ");
		list.add("tarjeta_mconsumototal_AVG [s] ");
		list.add("tarjeta_mpagominimo_AVG [s] ");
		list.add("marketing_activo_ultimos90dias_AVG [n] ");
		list.add("cliente_vip_AVG [n] ");
		list.add("cliente_sucursal_AVG [s] ");
		list.add("cliente_edad_AVG [s] ");
		list.add("cliente_antiguedad_AVG [s] ");
		list.add("mrentabilidad_annual_AVG [n] ");
		list.add("marketing_coss_selling_AVG [n] ");
		list.add("mcuenta_corriente_dolares_AVG [n] ");
		list.add("ctarjeta_debito_transacciones_AVG [s] ");
		list.add("ctarjeta_visa_transacciones_AVG [s] ");
		list.add("ctarjeta_master_transacciones_AVG [s] ");
		list.add("cprestamos_personales_AVG [s] ");
		list.add("cprestamos_prendarios_AVG [n] ");
		list.add("cprestamos_hipotecarios_AVG [n] ");
		list.add("mbonos_gobierno_AVG [n] ");
		list.add("mmonedas_extranjeras_AVG [n] ");
		list.add("minversiones_otras_AVG [n] ");
		list.add("cplan_sueldo_transaccion_AVG [n] ");
		list.add("ccajeros_propios_descuentos_AVG [n] ");
		list.add("ctarjeta_visa_descuentos_AVG [s] ");
		list.add("ctarjeta_master_descuentos_AVG [s] ");
		list.add("ccuenta_descuentos_AVG [n] ");
		list.add("mcuenta_descuentos_AVG [n] ");
		list.add("ccomisiones_mantenimiento_AVG [s] ");
		list.add("ccomisiones_otras_AVG [s] ");
		list.add("ccambio_monedas_compra_AVG [n] ");
		list.add("ccambio_monedas_venta_AVG [n] ");
		list.add("ctransferencias_recibidas_AVG [n] ");
		list.add("ctransferencias_emitidas_AVG [s] ");
		list.add("cextraccion_autoservicio_AVG [s] ");
		list.add("ccheques_depositados_AVG [s] ");
		list.add("ccheques_emitidos_AVG [s] ");
		list.add("ccheques_depositados_rechazados_AVG [n] ");
		list.add("ccheques_emitidos_rechazados_AVG [s] ");
		list.add("ccallcenter_transacciones_AVG [s] ");
		list.add("chomebanking_transacciones_AVG [s] ");
		list.add("cautoservicio_transacciones_AVG [n] ");
		list.add("ccajeros_propio_transacciones_AVG [s] ");
		list.add("ccajeros_ajenos_transacciones_AVG [s] ");
		list.add("Master_marca_atraso_AVG [n] ");
		list.add("Master_cuenta_estado_AVG [s] ");
		list.add("Master_Fvencimiento_AVG [s] ");
		list.add("Master_Finiciomora_AVG [s] ");
		list.add("Master_fultimo_cierre_AVG [s] ");
		list.add("Master_fechaalta_AVG [s] ");
		list.add("Visa_marca_atraso_AVG [n] ");
		list.add("Visa_cuenta_estado_AVG [s] ");
		list.add("Visa_Fvencimiento_AVG [s] ");
		list.add("Visa_Finiciomora_AVG [s] ");
		list.add("Visa_fultimo_cierre_AVG [s] ");
		list.add("Visa_fechaalta_AVG [s] ");
		list.add("jestado_seguros_AVG [n] ");
		list.add("jestado_debitos_AVG [n] ");
		list.add("jestado_contacto_AVG [n] ");
		list.add("jestado_visa_dias_mora_relativa_periodo_AVG [s] ");
		list.add("jestado_master_dias_mora_relativa_periodo_AVG [s] ");
		list.add("tarjeta_marca_atraso_AVG [n] ");
		list.add("tarjeta_cuenta_estado_AVG [s] ");
		list.add("jestado_tarjeta_dias_mora_relativa_periodo_AVG [s] ");
		list.add("tarjeta_tconsumos_AVG [n] ");
		list.add("tarjeta_tadelantosefectivo_AVG [n] ");
		list.add("mrentabilidad_MAX [s] ");
		list.add("mcomisiones_MAX [s] ");
		list.add("mactivos_margen_MAX [s] ");
		list.add("mpasivos_margen_MAX [s] ");
		list.add("mcuenta_corriente_Nopaquete_MAX [s] ");
		list.add("mcuenta_corriente_Paquete_MAX [s] ");
		list.add("mcaja_ahorro_Paquete_MAX [s] ");
		list.add("mcaja_ahorro_Nopaquete_MAX [s] ");
		list.add("mcaja_ahorro_dolares_MAX [s] ");
		list.add("mdescubierto_preacordado_MAX [s] ");
		list.add("mcuentas_saldo_MAX [s] ");
		list.add("mautoservicio_MAX [s] ");
		list.add("mtarjeta_visa_consumo_MAX [s] ");
		list.add("mtarjeta_master_consumo_MAX [s] ");
		list.add("mprestamos_personales_MAX [s] ");
		list.add("mprestamos_prendarios_MAX [s] ");
		list.add("mprestamos_hipotecarios_MAX [s] ");
		list.add("mplazo_fijo_dolares_MAX [s] ");
		list.add("mplazo_fijo_pesos_MAX [s] ");
		list.add("mfondos_comunes_inversion_pesos_MAX [s] ");
		list.add("mfondos_comunes_inversion_dolares_MAX [s] ");
		list.add("mtitulos_MAX [s] ");
		list.add("mplan_sueldo_MAX [s] ");
		list.add("mplan_sueldo_manual_MAX [s] ");
		list.add("mcuenta_debitos_automaticos_MAX [s] ");
		list.add("mttarjeta_visa_debitos_automaticos_MAX [s] ");
		list.add("mttarjeta_master_debitos_automaticos_MAX [s] ");
		list.add("mpagodeservicios_MAX [s] ");
		list.add("mpagomiscuentas_MAX [s] ");
		list.add("mcajeros_propios_descuentos_MAX [s] ");
		list.add("mtarjeta_visa_descuentos_MAX [s] ");
		list.add("mtarjeta_master_descuentos_MAX [s] ");
		list.add("mcomisiones_mantenimiento_MAX [s] ");
		list.add("mcomisiones_otras_MAX [s] ");
		list.add("mcambio_monedas_compra_MAX [s] ");
		list.add("mcambio_monedas_venta_MAX [s] ");
		list.add("mtransferencias_recibidas_MAX [s] ");
		list.add("mtransferencias_emitidas_MAX [s] ");
		list.add("mextraccion_autoservicio_MAX [s] ");
		list.add("mcheques_depositados_MAX [s] ");
		list.add("mcheques_emitidos_MAX [s] ");
		list.add("mcheques_depositados_rechazados_MAX [s] ");
		list.add("mcheques_emitidos_rechazados_MAX [s] ");
		list.add("mcajeros_propio_MAX [s] ");
		list.add("mcajeros_ajenos_MAX [s] ");
		list.add("Master_mfinanciacion_limite_MAX [s] ");
		list.add("Master_msaldototal_MAX [s] ");
		list.add("Master_msaldopesos_MAX [s] ");
		list.add("Master_msaldodolares_MAX [s] ");
		list.add("Master_mconsumospesos_MAX [s] ");
		list.add("Master_mconsumosdolares_MAX [s] ");
		list.add("Master_mlimitecompra_MAX [s] ");
		list.add("Master_madelantopesos_MAX [s] ");
		list.add("Master_madelantodolares_MAX [s] ");
		list.add("Master_mpagado_MAX [s] ");
		list.add("Master_mpagospesos_MAX [s] ");
		list.add("Master_mpagosdolares_MAX [s] ");
		list.add("Master_mconsumototal_MAX [s] ");
		list.add("Master_mpagominimo_MAX [s] ");
		list.add("Visa_mfinanciacion_limite_MAX [s] ");
		list.add("Visa_msaldototal_MAX [s] ");
		list.add("Visa_msaldopesos_MAX [s] ");
		list.add("Visa_msaldodolares_MAX [s] ");
		list.add("Visa_mconsumospesos_MAX [s] ");
		list.add("Visa_mconsumosdolares_MAX [s] ");
		list.add("Visa_mlimitecompra_MAX [s] ");
		list.add("Visa_madelantopesos_MAX [s] ");
		list.add("Visa_madelantodolares_MAX [s] ");
		list.add("Visa_mpagado_MAX [s] ");
		list.add("Visa_mpagospesos_MAX [s] ");
		list.add("Visa_mpagosdolares_MAX [s] ");
		list.add("Visa_mconsumototal_MAX [s] ");
		list.add("Visa_mpagominimo_MAX [s] ");
		list.add("jestado_visa_ratio_consumo_limite_MAX [s] ");
		list.add("jestado_master_ratio_consumo_limite_MAX [s] ");
		list.add("jestado_visa_ratio_minimo_consumo_limite_MAX [s] ");
		list.add("jestado_master_ratio_minimo_consumo_limite_MAX [s] ");
		list.add("tarjeta_mfinanciacion_limite_MAX [s] ");
		list.add("jestado_tarjeta_ratio_consumo_limite_MAX [s] ");
		list.add("jestado_tarjeta_ratio_minimo_consumo_limite_MAX [s] ");
		list.add("tarjeta_msaldototal_MAX [s] ");
		list.add("tarjeta_msaldopesos_MAX [s] ");
		list.add("tarjeta_msaldodolares_MAX [s] ");
		list.add("tarjeta_mconsumospesos_MAX [s] ");
		list.add("tarjeta_mconsumosdolares_MAX [s] ");
		list.add("tarjeta_mlimitecompra_MAX [s] ");
		list.add("tarjeta_madelantopesos_MAX [s] ");
		list.add("tarjeta_madelantodolares_MAX [s] ");
		list.add("tarjeta_mpagado_MAX [s] ");
		list.add("tarjeta_mpagospesos_MAX [s] ");
		list.add("tarjeta_mpagosdolares_MAX [s] ");
		list.add("tarjeta_mconsumototal_MAX [s] ");
		list.add("tarjeta_mpagominimo_MAX [s] ");
		list.add("marketing_activo_ultimos90dias_MAX [n] ");
		list.add("cliente_vip_MAX [n] ");
		list.add("cliente_sucursal_MAX [s] ");
		list.add("cliente_edad_MAX [s] ");
		list.add("cliente_antiguedad_MAX [s] ");
		list.add("mrentabilidad_annual_MAX [n] ");
		list.add("marketing_coss_selling_MAX [n] ");
		list.add("mcuenta_corriente_dolares_MAX [n] ");
		list.add("ctarjeta_debito_transacciones_MAX [s] ");
		list.add("ctarjeta_visa_transacciones_MAX [s] ");
		list.add("ctarjeta_master_transacciones_MAX [s] ");
		list.add("cprestamos_personales_MAX [s] ");
		list.add("cprestamos_prendarios_MAX [n] ");
		list.add("cprestamos_hipotecarios_MAX [n] ");
		list.add("mbonos_gobierno_MAX [n] ");
		list.add("mmonedas_extranjeras_MAX [n] ");
		list.add("minversiones_otras_MAX [n] ");
		list.add("cplan_sueldo_transaccion_MAX [n] ");
		list.add("ccajeros_propios_descuentos_MAX [n] ");
		list.add("ctarjeta_visa_descuentos_MAX [s] ");
		list.add("ctarjeta_master_descuentos_MAX [s] ");
		list.add("ccuenta_descuentos_MAX [n] ");
		list.add("mcuenta_descuentos_MAX [n] ");
		list.add("ccomisiones_mantenimiento_MAX [s] ");
		list.add("ccomisiones_otras_MAX [s] ");
		list.add("ccambio_monedas_compra_MAX [n] ");
		list.add("ccambio_monedas_venta_MAX [n] ");
		list.add("ctransferencias_recibidas_MAX [n] ");
		list.add("ctransferencias_emitidas_MAX [s] ");
		list.add("cextraccion_autoservicio_MAX [s] ");
		list.add("ccheques_depositados_MAX [s] ");
		list.add("ccheques_emitidos_MAX [s] ");
		list.add("ccheques_depositados_rechazados_MAX [n] ");
		list.add("ccheques_emitidos_rechazados_MAX [s] ");
		list.add("ccallcenter_transacciones_MAX [s] ");
		list.add("chomebanking_transacciones_MAX [s] ");
		list.add("cautoservicio_transacciones_MAX [n] ");
		list.add("ccajeros_propio_transacciones_MAX [s] ");
		list.add("ccajeros_ajenos_transacciones_MAX [s] ");
		list.add("Master_marca_atraso_MAX [n] ");
		list.add("Master_cuenta_estado_MAX [s] ");
		list.add("Master_Fvencimiento_MAX [s] ");
		list.add("Master_Finiciomora_MAX [s] ");
		list.add("Master_fultimo_cierre_MAX [s] ");
		list.add("Master_fechaalta_MAX [s] ");
		list.add("Visa_marca_atraso_MAX [n] ");
		list.add("Visa_cuenta_estado_MAX [s] ");
		list.add("Visa_Fvencimiento_MAX [s] ");
		list.add("Visa_Finiciomora_MAX [s] ");
		list.add("Visa_fultimo_cierre_MAX [s] ");
		list.add("Visa_fechaalta_MAX [s] ");
		list.add("jestado_seguros_MAX [n] ");
		list.add("jestado_debitos_MAX [n] ");
		list.add("jestado_contacto_MAX [n] ");
		list.add("jestado_visa_dias_mora_relativa_periodo_MAX [s] ");
		list.add("jestado_master_dias_mora_relativa_periodo_MAX [s] ");
		list.add("tarjeta_marca_atraso_MAX [s] ");
		list.add("tarjeta_cuenta_estado_MAX [s] ");
		list.add("jestado_tarjeta_dias_mora_relativa_periodo_MAX [s] ");
		list.add("tarjeta_tconsumos_MAX [n] ");
		list.add("tarjeta_tadelantosefectivo_MAX [n] ");
		list.add("mrentabilidad_MIN [s] ");
		list.add("mcomisiones_MIN [s] ");
		list.add("mactivos_margen_MIN [s] ");
		list.add("mpasivos_margen_MIN [s] ");
		list.add("mcuenta_corriente_Nopaquete_MIN [s] ");
		list.add("mcuenta_corriente_Paquete_MIN [s] ");
		list.add("mcaja_ahorro_Paquete_MIN [s] ");
		list.add("mcaja_ahorro_Nopaquete_MIN [s] ");
		list.add("mcaja_ahorro_dolares_MIN [s] ");
		list.add("mdescubierto_preacordado_MIN [s] ");
		list.add("mcuentas_saldo_MIN [s] ");
		list.add("mautoservicio_MIN [s] ");
		list.add("mtarjeta_visa_consumo_MIN [s] ");
		list.add("mtarjeta_master_consumo_MIN [s] ");
		list.add("mprestamos_personales_MIN [s] ");
		list.add("mprestamos_prendarios_MIN [s] ");
		list.add("mprestamos_hipotecarios_MIN [s] ");
		list.add("mplazo_fijo_dolares_MIN [s] ");
		list.add("mplazo_fijo_pesos_MIN [s] ");
		list.add("mfondos_comunes_inversion_pesos_MIN [s] ");
		list.add("mfondos_comunes_inversion_dolares_MIN [s] ");
		list.add("mtitulos_MIN [s] ");
		list.add("mplan_sueldo_MIN [s] ");
		list.add("mplan_sueldo_manual_MIN [s] ");
		list.add("mcuenta_debitos_automaticos_MIN [s] ");
		list.add("mttarjeta_visa_debitos_automaticos_MIN [s] ");
		list.add("mttarjeta_master_debitos_automaticos_MIN [s] ");
		list.add("mpagodeservicios_MIN [s] ");
		list.add("mpagomiscuentas_MIN [s] ");
		list.add("mcajeros_propios_descuentos_MIN [s] ");
		list.add("mtarjeta_visa_descuentos_MIN [s] ");
		list.add("mtarjeta_master_descuentos_MIN [s] ");
		list.add("mcomisiones_mantenimiento_MIN [s] ");
		list.add("mcomisiones_otras_MIN [s] ");
		list.add("mcambio_monedas_compra_MIN [s] ");
		list.add("mcambio_monedas_venta_MIN [s] ");
		list.add("mtransferencias_recibidas_MIN [s] ");
		list.add("mtransferencias_emitidas_MIN [s] ");
		list.add("mextraccion_autoservicio_MIN [s] ");
		list.add("mcheques_depositados_MIN [s] ");
		list.add("mcheques_emitidos_MIN [s] ");
		list.add("mcheques_depositados_rechazados_MIN [s] ");
		list.add("mcheques_emitidos_rechazados_MIN [s] ");
		list.add("mcajeros_propio_MIN [s] ");
		list.add("mcajeros_ajenos_MIN [s] ");
		list.add("Master_mfinanciacion_limite_MIN [s] ");
		list.add("Master_msaldototal_MIN [s] ");
		list.add("Master_msaldopesos_MIN [s] ");
		list.add("Master_msaldodolares_MIN [s] ");
		list.add("Master_mconsumospesos_MIN [s] ");
		list.add("Master_mconsumosdolares_MIN [s] ");
		list.add("Master_mlimitecompra_MIN [s] ");
		list.add("Master_madelantopesos_MIN [s] ");
		list.add("Master_madelantodolares_MIN [s] ");
		list.add("Master_mpagado_MIN [s] ");
		list.add("Master_mpagospesos_MIN [s] ");
		list.add("Master_mpagosdolares_MIN [s] ");
		list.add("Master_mconsumototal_MIN [s] ");
		list.add("Master_mpagominimo_MIN [s] ");
		list.add("Visa_mfinanciacion_limite_MIN [s] ");
		list.add("Visa_msaldototal_MIN [s] ");
		list.add("Visa_msaldopesos_MIN [s] ");
		list.add("Visa_msaldodolares_MIN [s] ");
		list.add("Visa_mconsumospesos_MIN [s] ");
		list.add("Visa_mconsumosdolares_MIN [s] ");
		list.add("Visa_mlimitecompra_MIN [s] ");
		list.add("Visa_madelantopesos_MIN [s] ");
		list.add("Visa_madelantodolares_MIN [s] ");
		list.add("Visa_mpagado_MIN [s] ");
		list.add("Visa_mpagospesos_MIN [s] ");
		list.add("Visa_mpagosdolares_MIN [s] ");
		list.add("Visa_mconsumototal_MIN [s] ");
		list.add("Visa_mpagominimo_MIN [s] ");
		list.add("jestado_visa_ratio_consumo_limite_MIN [s] ");
		list.add("jestado_master_ratio_consumo_limite_MIN [s] ");
		list.add("jestado_visa_ratio_minimo_consumo_limite_MIN [s] ");
		list.add("jestado_master_ratio_minimo_consumo_limite_MIN [s] ");
		list.add("tarjeta_mfinanciacion_limite_MIN [s] ");
		list.add("jestado_tarjeta_ratio_consumo_limite_MIN [s] ");
		list.add("jestado_tarjeta_ratio_minimo_consumo_limite_MIN [s] ");
		list.add("tarjeta_msaldototal_MIN [s] ");
		list.add("tarjeta_msaldopesos_MIN [s] ");
		list.add("tarjeta_msaldodolares_MIN [s] ");
		list.add("tarjeta_mconsumospesos_MIN [s] ");
		list.add("tarjeta_mconsumosdolares_MIN [s] ");
		list.add("tarjeta_mlimitecompra_MIN [s] ");
		list.add("tarjeta_madelantopesos_MIN [s] ");
		list.add("tarjeta_madelantodolares_MIN [s] ");
		list.add("tarjeta_mpagado_MIN [s] ");
		list.add("tarjeta_mpagospesos_MIN [s] ");
		list.add("tarjeta_mpagosdolares_MIN [s] ");
		list.add("tarjeta_mconsumototal_MIN [s] ");
		list.add("tarjeta_mpagominimo_MIN [s] ");
		list.add("marketing_activo_ultimos90dias_MIN [n] ");
		list.add("cliente_vip_MIN [n] ");
		list.add("cliente_sucursal_MIN [s] ");
		list.add("cliente_edad_MIN [s] ");
		list.add("cliente_antiguedad_MIN [s] ");
		list.add("mrentabilidad_annual_MIN [n] ");
		list.add("marketing_coss_selling_MIN [n] ");
		list.add("mcuenta_corriente_dolares_MIN [n] ");
		list.add("ctarjeta_debito_transacciones_MIN [s] ");
		list.add("ctarjeta_visa_transacciones_MIN [s] ");
		list.add("ctarjeta_master_transacciones_MIN [s] ");
		list.add("cprestamos_personales_MIN [s] ");
		list.add("cprestamos_prendarios_MIN [n] ");
		list.add("cprestamos_hipotecarios_MIN [n] ");
		list.add("mbonos_gobierno_MIN [n] ");
		list.add("mmonedas_extranjeras_MIN [n] ");
		list.add("minversiones_otras_MIN [n] ");
		list.add("cplan_sueldo_transaccion_MIN [n] ");
		list.add("ccajeros_propios_descuentos_MIN [n] ");
		list.add("ctarjeta_visa_descuentos_MIN [s] ");
		list.add("ctarjeta_master_descuentos_MIN [s] ");
		list.add("ccuenta_descuentos_MIN [n] ");
		list.add("mcuenta_descuentos_MIN [n] ");
		list.add("ccomisiones_mantenimiento_MIN [s] ");
		list.add("ccomisiones_otras_MIN [s] ");
		list.add("ccambio_monedas_compra_MIN [n] ");
		list.add("ccambio_monedas_venta_MIN [n] ");
		list.add("ctransferencias_recibidas_MIN [n] ");
		list.add("ctransferencias_emitidas_MIN [s] ");
		list.add("cextraccion_autoservicio_MIN [s] ");
		list.add("ccheques_depositados_MIN [s] ");
		list.add("ccheques_emitidos_MIN [s] ");
		list.add("ccheques_depositados_rechazados_MIN [n] ");
		list.add("ccheques_emitidos_rechazados_MIN [s] ");
		list.add("ccallcenter_transacciones_MIN [s] ");
		list.add("chomebanking_transacciones_MIN [s] ");
		list.add("cautoservicio_transacciones_MIN [n] ");
		list.add("ccajeros_propio_transacciones_MIN [s] ");
		list.add("ccajeros_ajenos_transacciones_MIN [s] ");
		list.add("Master_marca_atraso_MIN [n] ");
		list.add("Master_cuenta_estado_MIN [s] ");
		list.add("Master_Fvencimiento_MIN [s] ");
		list.add("Master_Finiciomora_MIN [s] ");
		list.add("Master_fultimo_cierre_MIN [s] ");
		list.add("Master_fechaalta_MIN [s] ");
		list.add("Visa_marca_atraso_MIN [n] ");
		list.add("Visa_cuenta_estado_MIN [s] ");
		list.add("Visa_Fvencimiento_MIN [s] ");
		list.add("Visa_Finiciomora_MIN [s] ");
		list.add("Visa_fultimo_cierre_MIN [s] ");
		list.add("Visa_fechaalta_MIN [s] ");
		list.add("jestado_seguros_MIN [n] ");
		list.add("jestado_debitos_MIN [n] ");
		list.add("jestado_contacto_MIN [n] ");
		list.add("jestado_visa_dias_mora_relativa_periodo_MIN [s] ");
		list.add("jestado_master_dias_mora_relativa_periodo_MIN [s] ");
		list.add("tarjeta_marca_atraso_MIN [s] ");
		list.add("tarjeta_cuenta_estado_MIN [s] ");
		list.add("jestado_tarjeta_dias_mora_relativa_periodo_MIN [s] ");
		list.add("tarjeta_tconsumos_MIN [n] ");
		list.add("tarjeta_tadelantosefectivo_MIN [n] ");
		return list;
	}
	
	public static List<String> getCampos() {
		List<String> campos = new ArrayList<String>();
		campos.add("clase [n] ");
		campos.add("numero_de_cliente [s] ");
		campos.add("foto_mes [s] ");
		campos.add("marketing_activo_ultimos90dias [n] ");
		campos.add("cliente_vip [n] ");
		campos.add("cliente_sucursal [s] ");
		campos.add("cliente_edad [s] ");
		campos.add("cliente_antiguedad [s] ");
		campos.add("mrentabilidad [s] ");
		campos.add("mrentabilidad_annual [n] ");
		campos.add("mcomisiones [s] ");
		campos.add("mactivos_margen [s] ");
		campos.add("mpasivos_margen [s] ");
		campos.add("marketing_coss_selling [n] ");
		campos.add("tpaquete1 [n] ");
		campos.add("tpaquete2 [n] ");
		campos.add("tpaquete3 [n] ");
		campos.add("tpaquete4 [n] ");
		campos.add("tpaquete5 [n] ");
		campos.add("tpaquete6 [n] ");
		campos.add("tpaquete7 [n] ");
		campos.add("tpaquete8 [n] ");
		campos.add("tpaquete9 [n] ");
		campos.add("tcuentas [n] ");
		campos.add("tcuenta_corriente [n] ");
		campos.add("mcuenta_corriente_Nopaquete [s] ");
		campos.add("mcuenta_corriente_Paquete [s] ");
		campos.add("mcuenta_corriente_dolares [n] ");
		campos.add("tcaja_ahorro [n] ");
		campos.add("mcaja_ahorro_Paquete [s] ");
		campos.add("mcaja_ahorro_Nopaquete [s] ");
		campos.add("mcaja_ahorro_dolares [s] ");
		campos.add("mdescubierto_preacordado [s] ");
		campos.add("mcuentas_saldo [s] ");
		campos.add("ttarjeta_debito [n] ");
		campos.add("ctarjeta_debito_transacciones [s] ");
		campos.add("mautoservicio [s] ");
		campos.add("ttarjeta_visa [n] ");
		campos.add("ctarjeta_visa_transacciones [s] ");
		campos.add("mtarjeta_visa_consumo [s] ");
		campos.add("ttarjeta_master [n] ");
		campos.add("ctarjeta_master_transacciones [s] ");
		campos.add("mtarjeta_master_consumo [s] ");
		campos.add("cprestamos_personales [s] ");
		campos.add("mprestamos_personales [s] ");
		campos.add("cprestamos_prendarios [n] ");
		campos.add("mprestamos_prendarios [s] ");
		campos.add("cprestamos_hipotecarios [n] ");
		campos.add("mprestamos_hipotecarios [s] ");
		campos.add("tplazo_fijo [n] ");
		campos.add("mplazo_fijo_dolares [s] ");
		campos.add("mplazo_fijo_pesos [s] ");
		campos.add("tfondos_comunes_inversion [n] ");
		campos.add("mfondos_comunes_inversion_pesos [s] ");
		campos.add("mfondos_comunes_inversion_dolares [s] ");
		campos.add("ttitulos [n] ");
		campos.add("mtitulos [s] ");
		campos.add("tseguro_vida_mercado_abierto [n] ");
		campos.add("tseguro_auto [n] ");
		campos.add("tseguro_vivienda [n] ");
		campos.add("tseguro_accidentes_personales [n] ");
		campos.add("tcaja_seguridad [n] ");
		campos.add("mbonos_gobierno [n] ");
		campos.add("mmonedas_extranjeras [n] ");
		campos.add("minversiones_otras [n] ");
		campos.add("tplan_sueldo [n] ");
		campos.add("mplan_sueldo [s] ");
		campos.add("mplan_sueldo_manual [s] ");
		campos.add("cplan_sueldo_transaccion [n] ");
		campos.add("tcuenta_debitos_automaticos [n] ");
		campos.add("mcuenta_debitos_automaticos [s] ");
		campos.add("ttarjeta_visa_debitos_automaticos [n] ");
		campos.add("mttarjeta_visa_debitos_automaticos [s] ");
		campos.add("ttarjeta_master_debitos_automaticos [n] ");
		campos.add("mttarjeta_master_debitos_automaticos [s] ");
		campos.add("tpagodeservicios [n] ");
		campos.add("mpagodeservicios [s] ");
		campos.add("tpagomiscuentas [n] ");
		campos.add("mpagomiscuentas [s] ");
		campos.add("ccajeros_propios_descuentos [n] ");
		campos.add("mcajeros_propios_descuentos [s] ");
		campos.add("ctarjeta_visa_descuentos [s] ");
		campos.add("mtarjeta_visa_descuentos [s] ");
		campos.add("ctarjeta_master_descuentos [s] ");
		campos.add("mtarjeta_master_descuentos [s] ");
		campos.add("ccuenta_descuentos [n] ");
		campos.add("mcuenta_descuentos [n] ");
		campos.add("ccomisiones_mantenimiento [s] ");
		campos.add("mcomisiones_mantenimiento [s] ");
		campos.add("ccomisiones_otras [s] ");
		campos.add("mcomisiones_otras [s] ");
		campos.add("tcambio_monedas [n] ");
		campos.add("ccambio_monedas_compra [n] ");
		campos.add("mcambio_monedas_compra [s] ");
		campos.add("ccambio_monedas_venta [n] ");
		campos.add("mcambio_monedas_venta [s] ");
		campos.add("ctransferencias_recibidas [n] ");
		campos.add("mtransferencias_recibidas [s] ");
		campos.add("ctransferencias_emitidas [s] ");
		campos.add("mtransferencias_emitidas [s] ");
		campos.add("cextraccion_autoservicio [s] ");
		campos.add("mextraccion_autoservicio [s] ");
		campos.add("ccheques_depositados [s] ");
		campos.add("mcheques_depositados [s] ");
		campos.add("ccheques_emitidos [s] ");
		campos.add("mcheques_emitidos [s] ");
		campos.add("ccheques_depositados_rechazados [n] ");
		campos.add("mcheques_depositados_rechazados [s] ");
		campos.add("ccheques_emitidos_rechazados [s] ");
		campos.add("mcheques_emitidos_rechazados [s] ");
		campos.add("tcallcenter [n] ");
		campos.add("ccallcenter_transacciones [s] ");
		campos.add("thomebanking [n] ");
		campos.add("chomebanking_transacciones [s] ");
		campos.add("tautoservicio [n] ");
		campos.add("cautoservicio_transacciones [n] ");
		campos.add("tcajas [n] ");
		campos.add("tcajas_consultas [n] ");
		campos.add("tcajas_depositos [n] ");
		campos.add("tcajas_extracciones [n] ");
		campos.add("tcajas_otras [n] ");
		campos.add("ccajeros_propio_transacciones [s] ");
		campos.add("mcajeros_propio [s] ");
		campos.add("ccajeros_ajenos_transacciones [s] ");
		campos.add("mcajeros_ajenos [s] ");
		campos.add("tmovimientos_ultimos90dias [n] ");
		campos.add("Master_marca_atraso [n] ");
		campos.add("Master_cuenta_estado [s] ");
		campos.add("Master_mfinanciacion_limite [s] ");
		campos.add("Master_Fvencimiento [s] ");
		campos.add("Master_Finiciomora [s] ");
		campos.add("Master_msaldototal [s] ");
		campos.add("Master_msaldopesos [s] ");
		campos.add("Master_msaldodolares [s] ");
		campos.add("Master_mconsumospesos [s] ");
		campos.add("Master_mconsumosdolares [s] ");
		campos.add("Master_mlimitecompra [s] ");
		campos.add("Master_madelantopesos [s] ");
		campos.add("Master_madelantodolares [s] ");
		campos.add("Master_fultimo_cierre [s] ");
		campos.add("Master_mpagado [s] ");
		campos.add("Master_mpagospesos [s] ");
		campos.add("Master_mpagosdolares [s] ");
		campos.add("Master_fechaalta [s] ");
		campos.add("Master_mconsumototal [s] ");
		campos.add("Master_tconsumos [n] ");
		campos.add("Master_tadelantosefectivo [n] ");
		campos.add("Master_mpagominimo [s] ");
		campos.add("Visa_marca_atraso [n] ");
		campos.add("Visa_cuenta_estado [s] ");
		campos.add("Visa_mfinanciacion_limite [s] ");
		campos.add("Visa_Fvencimiento [s] ");
		campos.add("Visa_Finiciomora [s] ");
		campos.add("Visa_msaldototal [s] ");
		campos.add("Visa_msaldopesos [s] ");
		campos.add("Visa_msaldodolares [s] ");
		campos.add("Visa_mconsumospesos [s] ");
		campos.add("Visa_mconsumosdolares [s] ");
		campos.add("Visa_mlimitecompra [s] ");
		campos.add("Visa_madelantopesos [s] ");
		campos.add("Visa_madelantodolares [s] ");
		campos.add("Visa_fultimo_cierre [s] ");
		campos.add("Visa_mpagado [s] ");
		campos.add("Visa_mpagospesos [s] ");
		campos.add("Visa_mpagosdolares [s] ");
		campos.add("Visa_fechaalta [s] ");
		campos.add("Visa_mconsumototal [s] ");
		campos.add("Visa_tconsumos [n] ");
		campos.add("Visa_tadelantosefectivo [n] ");
		campos.add("Visa_mpagominimo [s] ");
		campos.add("participa [n] ");
		campos.add("jestado_seguros [n] ");
		campos.add("jestado_debitos [n] ");
		campos.add("jestado_contacto [n] ");
		campos.add("jestado_visa_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_master_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_visa_ratio_consumo_limite [s] ");
		campos.add("jestado_master_ratio_consumo_limite [s] ");
		campos.add("jestado_visa_ratio_minimo_consumo_limite [s] ");
		campos.add("jestado_master_ratio_minimo_consumo_limite [s] ");
		campos.add("tarjeta_marca_atraso [n] ");
		campos.add("tarjeta_cuenta_estado [s] ");
		campos.add("tarjeta_mfinanciacion_limite [s] ");
		campos.add("tarjeta_Fvencimiento [n] ");
		campos.add("tarjeta_Finiciomora [n] ");
		campos.add("jestado_tarjeta_dias_mora_relativa_periodo [s] ");
		campos.add("jestado_tarjeta_ratio_consumo_limite [s] ");
		campos.add("jestado_tarjeta_ratio_minimo_consumo_limite [s] ");
		campos.add("tarjeta_msaldototal [s] ");
		campos.add("tarjeta_msaldopesos [s] ");
		campos.add("tarjeta_msaldodolares [s] ");
		campos.add("tarjeta_mconsumospesos [s] ");
		campos.add("tarjeta_mconsumosdolares [s] ");
		campos.add("tarjeta_mlimitecompra [s] ");
		campos.add("tarjeta_madelantopesos [s] ");
		campos.add("tarjeta_madelantodolares [s] ");
		campos.add("tarjeta_fultimo_cierre [n] ");
		campos.add("tarjeta_mpagado [s] ");
		campos.add("tarjeta_mpagospesos [s] ");
		campos.add("tarjeta_mpagosdolares [s] ");
		campos.add("tarjeta_fechaalta [n] ");
		campos.add("tarjeta_mconsumototal [s] ");
		campos.add("tarjeta_tconsumos [n] ");
		campos.add("tarjeta_tadelantosefectivo [n] ");
		campos.add("tarjeta_mpagominimo [s] ");
		campos.add("mrentabilidad_AVG [s] ");
		campos.add("mcomisiones_AVG [s] ");
		campos.add("Master_mfinanciacion_limite_AVG [s] ");
		campos.add("Master_msaldodolares_AVG [s] ");
		campos.add("Master_mconsumospesos_AVG [s] ");
		campos.add("Master_mconsumosdolares_AVG [s] ");
		campos.add("Master_mlimitecompra_AVG [s] ");
		campos.add("Master_madelantodolares_AVG [s] ");
		campos.add("Master_mpagado_AVG [s] ");
		campos.add("Master_mpagosdolares_AVG [s] ");
		campos.add("Master_mconsumototal_AVG [s] ");
		campos.add("Visa_mfinanciacion_limite_AVG [s] ");
		campos.add("Visa_mlimitecompra_AVG [s] ");
		campos.add("Visa_madelantodolares_AVG [s] ");
		campos.add("Visa_mpagado_AVG [s] ");
		campos.add("jestado_visa_ratio_consumo_limite_AVG [s] ");
		campos.add("tarjeta_mfinanciacion_limite_AVG [s] ");
		campos.add("tarjeta_msaldodolares_AVG [s] ");
		campos.add("tarjeta_mconsumospesos_AVG [s] ");
		campos.add("tarjeta_mconsumosdolares_AVG [s] ");
		campos.add("tarjeta_mlimitecompra_AVG [s] ");
		campos.add("tarjeta_madelantodolares_AVG [s] ");
		campos.add("tarjeta_mpagosdolares_AVG [s] ");
		campos.add("tarjeta_mconsumototal_AVG [s] ");
		return campos;
	}
	
	public static Map<String, String> getAsMap(List<String> campos) {
		
		 Map<String, String> mapa = new HashMap<String, String>();
		 
		 for (String current : campos) {
			 String[]  tmp = current.split(" ");
			 mapa.put(tmp[0].trim() , current);
		}
		return mapa;
	}
	/**
	 * recibe los nombres de los cmapos
	 * arma el sql acorde a spss maximo 4 campos por linea y no deben exceder 90 caracters
	 * sin partir como: 
	 * 'cautoservicio_transacciones, tcajas, tcajas_consultas, tcajas_depositos, tcajas_extracciones, '+",
	 * @param lista
	 * @return
	 */
	public static  String transformToSpssSql(Collection<String> lista){
		int cota = 1;
		boolean lastcoma = true;
		StringBuilder build = new StringBuilder();
		
		String lineEnd = " '+\n";
		String lineInit = "'";
		build.append("TREE clase [n] BY "); 
		build.append("'");
		for (String campo : lista) {
			
			if (campo.equalsIgnoreCase("clase")) {
				// salta al siguiente
				continue;
			}
			
			lastcoma = true;
			if (cota == 5) {
				lastcoma = false;
				build.append("'+\n");
				build.append("'");
				cota=0;
			}
			build.append(campo).append(", ");
			cota++;
		}
		if (lastcoma) {
			return build.substring(0, build.length()-2);
		}else{
			return build.substring(0, build.length()-4);
		}
	}
	/** FIXME: SEGUIR CON LA TRANSFORMACIONDE FIELDS
	 * recibe los params con tipos   y no deben exceder 90 caracters por linea
	 * "mtarjeta_master_descuentos [s]  ccuenta_descuentos [n]  mcuenta_descuentos [n]  ccomisiones_mantenimiento [s]  mcomisiones_mantenimiento [s]  ccomisiones_otras [s]  mcomisiones_otras [s]  tcambio_monedas [n]  ccambio_monedas_compra [n]  mcambio_monedas_compra [s]  ccambio_monedas_venta [n]  mcambio_monedas_venta [s]  ctransferencias_recibidas [n]  mtransferencias_recibidas [s]  ctransferencias_emitidas [s]  mtransferencias_emitidas [s]  cextraccion_autoservicio [s]  mextraccion_autoservicio [s]  ccheques_depositados [s] ",
	 * @param sql
	 * @return
	 */
	public static  String transformToSpssTreeFields(String sql){
		int cota = 1;
		boolean lastcoma = true;
		StringBuilder build = new StringBuilder();
		build.append("");
		if (sql.contains("SELECT")) {
			
			String camposSelect =  sql.substring(sql.indexOf("SELECT ")+7,sql.indexOf(" FROM"));
			String[] campos = camposSelect.split(",");
			/* busco en mapa campos cada volor que estoy buscando y lo voy agregando */
			String  acumulador = "";
			String separator = System.getProperty("line.separator");
			Map<String, String> mapa = getAsMap(getCamposVersionConHistoyVars());
			String lineEnd = " "+separator;
			String lineInit = "";
			/*
			 * la idea aca es transformar los campos de sql a campos de arbol de spss
			 * */
			boolean es1ro = true;
			if (camposSelect.length() > 90) {
	//			acumulador
				for (String campo : campos) {
						if (acumulador.length() < 70) {
							acumulador+=mapa.get(campo.trim())+",";	
						}else{
							if (es1ro) {
								build.append(acumulador).append(lineEnd);
								es1ro = false;
							}else{
								build.append(lineInit).append(acumulador).append(lineEnd);	
							}
							
	//						build.append(lineInit).append(acumulador).append(lineEnd);
							acumulador="";
							acumulador+=mapa.get(campo.trim())+",";
							
						}
				}
			}
			if (lastcoma) {
				return build.substring(0, build.length()-4);
			}else{
				return build.toString();
			}
		}else{
			throw new RuntimeException ("Solo banco SELECT (si en mayuscula) ");
		}
	}
	
	public static Map<String, String> getCamposAUsarRandom(int randCount, List<String> listado) {
		Map<String, String> mapa  = getAsMap(listado);
		List<String> tmpRand = new ArrayList<String>();
		tmpRand.addAll(mapa.keySet());
		Map<String, String> mapaSelected  = new HashMap<String, String>();
		for (int i = 0 ; i <randCount  ; i ++){
			String currentTmp = tmpRand.get(RandomUtils.nextInt(0, randCount));
			mapaSelected.put(currentTmp, mapa.get(currentTmp));
//			tmpRand.get()
		}
		
		return mapaSelected;
		
	}
	
	public static String transformSqltoSpss(String sql) {
//		 identificar campos
//		convertir en no mas de N ccaracteres pro linea
//		 tiene que quedar como
//		/SQL='SELECT numero_de_cliente, foto_mes, marketing_activo_ultimos90dias, cliente_vip, '+
		
		if (sql.contains("SELECT")) {
			String separator = System.getProperty("line.separator");
			String lineEnd = " '+"+separator;
			String lineInit = "'";
			
			StringBuilder build = new StringBuilder();
			build.append("/SQL='SELECT ");
//			parte del select hasta el form , el resto no me calienta tanto.
			
			String campos =  sql.substring(sql.indexOf("SELECT ")+7,sql.indexOf(" FROM"));
			boolean es1ro = true;
			if (campos.length() > 90) {
				String[] soloCampos = campos.split(",");
				String  acumulador = "";
				for (String campo : soloCampos) {
					if (acumulador.length() < 70) {
						acumulador+=campo+",";	
					}else{
						if (es1ro) {
							build.append(acumulador).append(lineEnd);
							es1ro = false;
						}else{
							build.append(lineInit).append(acumulador).append(lineEnd);	
						}
						
//						build.append(lineInit).append(acumulador).append(lineEnd);
						acumulador="";
						acumulador+=campo+",";
						
					}
				}
				if (acumulador.length()!= 0) {
					if (es1ro) {
						build.append(acumulador).append(lineEnd);
						es1ro = false;
					}else{
						build.append(lineInit).append(acumulador).append(lineEnd);	
					}
				}
				
				
				build.deleteCharAt(build.lastIndexOf(","));
			}else{
				build.append(campos).append(lineEnd);
			}
			

			
//			parte del where 
			String restoConsulta =  sql.substring(sql.indexOf(" FROM"));
			restoConsulta = restoConsulta.replace(";", "");
			build.append(lineInit).append(restoConsulta).append("'");
			
			return build.toString();
		}else{
			throw new RuntimeException ("Solo banco SELECT (si en mayuscula) ");
		}
		
		
	}
	
}
