package business;

import java.util.ArrayList;


public class StatsUtils {
    public float[] y;
    public String[] x;
    public StatsUtils(){
        y = null;
        x = null;
    }
     public float[] allToGrams(float[] x, String[] y){
        float[] nexX = new float[x.length];
        for(int a = 0; a < x.length;a++){
            nexX[a] = x[a]*toGrams(y[a]);
        }
        return nexX;
    }
     public float toGrams(String y){
        float val = 1;
        if(y.equals("mg")){
            val = 0.001f;
        }
        if(y.equals("ug")){
            val = 0.000001f;
        }
        return val;
    }
     public void RemoveZeroTerms(String[] xData, float[] yData){
        float totalValue = 0.f;
        ArrayList<Float> yDataNew = new  ArrayList<Float>();
        ArrayList<String> xDataNew = new  ArrayList<String>();
        if(yData != null && xData != null && yData.length == xData.length){
            for(int x = 0; x< yData.length; x++){
                totalValue+= yData[x];
            }
            for(int x = 0; x< yData.length; x++){
                if(((float)yData[x]/(float)totalValue) > 0.01f){// get rid of anything less than one percent
                    yDataNew.add(yData[x]);
                    xDataNew.add(xData[x]);
                }
            }
            y = toFloat(yDataNew);
            x = toStringl(xDataNew);
        }
    }
     public float[] toFloat(ArrayList<Float> list){
        float[] newList = new float[list.size()];
        for(int x = 0; x< list.size(); x++){
            newList[x] = list.get(x);
        }
        return newList;
    }
     public String[] toStringl(ArrayList<String> list){
        String[] newList = new String[list.size()];
        for(int x = 0; x< list.size(); x++){
            newList[x] = list.get(x);
        }
        return newList;
    }
}
