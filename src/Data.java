import java.time.LocalDateTime;

public class Data {
    private final int dia;
    private final int mes;
    private final int ano;
    private final int hora;
    private final int minutos;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = 0;
        this.minutos = 0;
    }

    public Data(int dia, int mes, int ano, int hora, int minutos) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minutos = minutos;
    }

    public Data() {
        LocalDateTime currentTime = LocalDateTime.now();
        this.dia = currentTime.getDayOfMonth();
        this.mes = currentTime.getMonthValue();
        this.ano = currentTime.getYear();
        this.hora = currentTime.getHour();
        this.minutos = currentTime.getMinute();
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano + " " + hora + ":" + minutos;
    }
}
