package Entity;

public class StatisticsSummary {
    private int productId;
    private double percentageClick;
    private double percentageAddToCart;
    private double percentageCheckOut;

    // Constructor
    public StatisticsSummary(int productId, double percentageClick, double percentageAddToCart, double percentageCheckOut) {
        this.productId = productId;
        this.percentageClick = percentageClick;
        this.percentageAddToCart = percentageAddToCart;
        this.percentageCheckOut = percentageCheckOut;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPercentageClick() {
        return percentageClick;
    }

    public void setPercentageClick(double percentageClick) {
        this.percentageClick = percentageClick;
    }

    public double getPercentageAddToCart() {
        return percentageAddToCart;
    }

    public void setPercentageAddToCart(double percentageAddToCart) {
        this.percentageAddToCart = percentageAddToCart;
    }

    public double getPercentageCheckOut() {
        return percentageCheckOut;
    }

    public void setPercentageCheckOut(double percentageCheckOut) {
        this.percentageCheckOut = percentageCheckOut;
    }

    @Override
    public String toString() {
        return "StatisticsSummary{" +
                "productId=" + productId +
                ", percentageClick=" + percentageClick +
                ", percentageAddToCart=" + percentageAddToCart +
                ", percentageCheckOut=" + percentageCheckOut +
                '}';
    }
}
