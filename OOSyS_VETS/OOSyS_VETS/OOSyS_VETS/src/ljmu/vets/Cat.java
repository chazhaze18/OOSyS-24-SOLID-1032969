package ljmu.vets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Cat extends Pet implements Serializable {
    private static final long serialVersionUID = 1L;
    private Breeding breeding;

    public Cat(String name, LocalDate regDate, Breeding breeding) {
        super(name, regDate);
        this.breeding = breeding;
    }


	@Override
    public String toString() {
        return this.getClass().getSimpleName() + " >> " +
                this.name + " " +
                this.regDate.format(DateTimeFormatter.ofPattern("dd MMM yy")) + " " +
                this.breeding.toString();
    }

    // ToDo : get / set Methods ?
}
