import Entity.CRStatistics;
import Entity.StatisticsView;
import Service.FileService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String fileInPathStatistics = System.getProperty("user.dir").replace("/", "\\") + "/database/statistics.view.txt";
        FileService fileService = new FileService();
        List<StatisticsView> statisticsViews = fileService.readFileStatistics(fileInPathStatistics);


        //Data Analystics
        Map<CRStatistics, CRStatistics> dataCRS = statisticsViews.stream()
                .collect(Collectors.groupingBy(
                    cr -> new CRStatistics(cr.getId(), cr.getMonthOfYear(), cr.getYear()),
                    Collectors.collectingAndThen(
                            Collectors.toList(),
                            ListCR->{
                                CRStatistics crStatistics = new CRStatistics();
                                StatisticsView firstStatistics = ListCR.getFirst();
                                int totalView = ListCR.stream().mapToInt(StatisticsView::getNumberOfView).sum();
                                crStatistics.setId(firstStatistics.getId());
                                crStatistics.setMonth(firstStatistics.getMonthOfYear());
                                crStatistics.setYear(firstStatistics.getYear());
                                crStatistics.setTotalView(totalView);
                                crStatistics.setTotalAddToCart(ListCR.stream().mapToInt(StatisticsView::getAddToCart).sum());
                                crStatistics.setTotalCheckOut(ListCR.stream().mapToInt(StatisticsView::getCheckOut).sum());
                                return crStatistics;
                            }
                    )

                ));
        dataCRS.forEach((k,v) -> System.out.println(v));

        int totalViewAll = dataCRS.values().stream().mapToInt(CRStatistics::getTotalView).sum();
        int totalAddToCartAll = dataCRS.values().stream().mapToInt(CRStatistics::getTotalAddToCart).sum();
        int totalCheckOutAll = dataCRS.values().stream().mapToInt(CRStatistics::getTotalCheckOut).sum();

        dataCRS.forEach((k, v) -> {
            double percentageView = (v.getTotalView() * 100.0) / totalViewAll;
            double percentageAddToCart = (v.getTotalAddToCart() * 100.0) / totalAddToCartAll;
            double percentageCheckOut = (v.getTotalCheckOut() * 100.0) / totalCheckOutAll;

            System.out.printf("Product ID: %d, Month: %d, Year: %d\n", v.getId(), v.getMonth(), v.getYear());
            System.out.printf("View: %.2f%%, Add to Cart: %.2f%%, Checkout: %.2f%%\n\n",
                    percentageView, percentageAddToCart, percentageCheckOut);
        });
    }
}