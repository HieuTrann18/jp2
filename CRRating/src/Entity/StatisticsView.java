package Entity;

import java.time.LocalDate;

public class StatisticsView {
    private int id;
    private int numberOfView;
    private int addToCart;
    private int checkOut;
    private LocalDate createAtDate;

    public StatisticsView() {
    }

    public StatisticsView(int id, int numberOfView, int addToCart, int checkOut) {
        this.id = id;
        this.numberOfView = numberOfView;
        this.addToCart = addToCart;
        this.checkOut = checkOut;
    }


    public StatisticsView(int id, int numberOfView, int addToCart, int checkOut, LocalDate createAtDate) {
        this.id = id;
        this.numberOfView = numberOfView;
        this.addToCart = addToCart;
        this.checkOut = checkOut;
        this.createAtDate = createAtDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfView() {
        return numberOfView;
    }

    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(int addToCart) {
        this.addToCart = addToCart;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public LocalDate getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(LocalDate createAtDate) {
        this.createAtDate = createAtDate;
    }

    public int getMonthOfYear(){
        return this.createAtDate.getMonthValue();
    }

    public int getYear(){
        return this.createAtDate.getYear();
    }

    @Override
    public String toString() {
        return "StatisticsView{" +
                "id = " + id +
                ", numberOfView = " + numberOfView +
                ", addToCart = " + addToCart +
                ", checkOut = " + checkOut +
                ", createAtDate = " + createAtDate +
                '}';
    }
}
