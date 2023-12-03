package VIEW;
import MODEL.Inherited.Director;
import MODEL.Inherited.Trader;
import MODEL.Worker.Worker;


public class View {

    public <T extends Worker> void show(T object) {
        System.out.println("\nIdentyfikator PESEL\t\t    \t| " + object.getPesel() +
                "\nImię\t\t\t\t\t\t\t| " + object.getName() +
                "\nNazwisko\t\t\t\t\t\t| " + object.getSurname() +
                "\nStanowisko\t\t\t\t\t\t| " + object.getPosition() +
                "\nWynagrodzenie (zł)\t\t\t\t| " + object.getSalary());

        if (object instanceof Director) {
            Director director = (Director) object;
            System.out.println("\nTelefon służbowy numer\t\t\t| " + director.getPhoneNumber() +
                    "\nDodatek służbowy (zł)\t\t\t| " + director.getServiceAllowance() +
                    "\nKarta służbowa numer\t\t\t| " + director.getServiceCard() +
                    "\nLimit kosztów/miesiąc (zł)\t\t| " + director.getLimitCosts());
        } else if (object instanceof Trader) {
            Trader trader = (Trader) object;
            System.out.println("\nTelefon służbowy numer\t\t\t| " + trader.getPhoneNumber() +
                    "\nStawka prowizji (%)\t\t\t\t| " + trader.getCommissionRate() +
                    "\nLimit prowizji (zł)\t\t\t\t| " + trader.getLimitCommission());
        }
    }


}






