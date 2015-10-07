import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 10/7/15.
 */
public class AnalyticObject {
    private String user;
    private String[] keys;
    private Map<String, double[]> keyData;

    AnalyticObject(){
        this(null);
    }
    AnalyticObject(String user){
        keyData = new HashMap<String, double[]>();
        if(user != null){
            this.user = user;
            keys = FingerprintModel.getKeyList(this.user);
            for (int i = 0; i < keys.length; i++){
                keyData.put(keys[i],FingerprintModel.getKeyData(user,keys[i]));
            }
        }else{
            keys = FingerprintModel.getKeyList();
            for (int i = 0; i < keys.length; i++){
                keyData.put(keys[i],FingerprintModel.getKeyData(keys[i]));
            }
        }
    }

    public double[] getKeyData(String key){
        return keyData.get(key);
    }

    public double getTTest(String key, double[] array){
        return StatsPackage.tTest(keyData.get(key),array);
    }

    public double getTTest(String key, AnalyticObject analyticObject){
        return StatsPackage.tTest(keyData.get(key),analyticObject.getKeyData(key));
    }

    public double averageTTest(AnalyticObject analyticObject){
        double total = 0;
        double sets = 0;

        for (int i = 0; i < keys.length; i++) {
            //Conditions for tTest{
            if(analyticObject.getKeyData(keys[i]) != null){
                if(analyticObject.getKeyData(keys[i]).length > 3 && keyData.get(keys[i]).length > 3) {
                    total = total + StatsPackage.tTest(keyData.get(keys[i]), analyticObject.getKeyData(keys[i]));
                    sets++;
                }
            }
        }
        double avg = total/sets;
        return avg;
    }





}
