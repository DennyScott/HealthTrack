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
    public float[] GramsToAll(float[] x, String[] y){
        float[] nexX = new float[x.length];
        for(int a = 0; a < y.length;a++){
            nexX[a] = x[a]/toGrams(y[a]);
        }
        return nexX;
    }
     public float toGrams(String y){
        float val = 1;
        if(y.equals("mg")){
            val = 0.001f;
        }
        if(y.equals("μg")){
            val = 0.000001f;
        }
         if(y.equals("kJ")){
             val = 0.0000000000001f;
         }
         if(y.equals("kCal")){
             val = 0.0000000000001f;
         }
        return val;
    }
     public void RemoveZeroTerms(String[] xData, float[] yData, float energyOffSet){
        float totalValue = 0.f;
        ArrayList<Float> yDataNew = new  ArrayList<Float>();
        ArrayList<String> xDataNew = new  ArrayList<String>();
        if(yData != null && xData != null && yData.length == xData.length){
            for(int x = 0; x< yData.length; x++){
                totalValue+= yData[x];
            }
            totalValue-=energyOffSet;
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
        float offset = 0;
        return newList;
    }
    public float getOffSet(float[] temp1, String[] temp2){
        float offset = 0f;
        for(int i = 0; i<temp1.length;i++){
            if(temp2[i].equals("kCal")|| temp2[i].equals("kJ")){
                offset+= temp1[i];
            }
        }
        return offset;
    }
}
