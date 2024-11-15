package Service;

import Entity.StatisticsSummary;
import Entity.StatisticsView;
import General.IFileService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService implements IFileService<StatisticsView>{

    public FileService(){

    }



    @Override
    public List<StatisticsView> readFileStatistics(String fileInPath) {
        List<StatisticsView> statisticsViews = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInPath));
            String lineData;
            while((lineData = bufferedReader.readLine()) != null){
                StatisticsView statisticsView = new StatisticsView();
                if(!lineData.isEmpty()){
                    String[] arrData = lineData.split(";");
                    statisticsView.setId(Integer.parseInt(String.valueOf(arrData[0])));
                    statisticsView.setNumberOfView(Integer.parseInt(arrData[1]));
                    statisticsView.setAddToCart(Integer.parseInt(arrData[2]));
                    statisticsView.setCheckOut(Integer.parseInt(arrData[3]));
                    statisticsView.setCreateAtDate(LocalDate.parse(String.valueOf(arrData[4])));
                }
                statisticsViews.add(statisticsView);
            }
        }catch(IOException e){
            e.getCause();
        }
        return statisticsViews;
    }

    @Override
    public List<StatisticsView> writeFileStatistics(String fileOutPath) {
        return List.of();
    }
}
