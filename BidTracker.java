
public class BidTracker {
    private double BidTracker_Number;
    public double Tracker(String folder) {

        int frame = 0;
        int time=199;
        int Number_distance = 0;
        double sum_distance_i = 0;
        double sum_distance_j = 0;



        while (frame < 9) {

            //Process frame 0 to 8 to find Bids
            String str = String.valueOf(frame);
            double[] Center_i_first = new BidFinder().getCenter_i("data/"+folder+"/frame0000" + str + ".jpg");
            double[] Center_j_first = new BidFinder().getCenter_j("data/"+folder+"/frame0000" + str + ".jpg");

            //Get the Numbers of frame Bids
            int Size_first = new BidFinder().size("data/"+folder+"/frame0000" + str + ".jpg");

            System.out.println("This is time : "+time);
            time=time-1;

            //process the next frame
            String str_next = String.valueOf(frame + 1);
            double[] Center_i_second = new BidFinder().getCenter_i("data/"+folder+"/frame0000" + str_next + ".jpg");
            double[] Center_j_second = new BidFinder().getCenter_j("data/"+folder+"/frame0000" + str_next + ".jpg");

            //Get the Numbers of next frame Bids
            int Size_second = new BidFinder().size("data/"+folder+"/frame0000" + str_next + ".jpg");

            double distance_min_i = 640;
            double distance_min_j = 640;
            double distance_min = 640;

            //comparison of the distance between the Bids of each frame with the next frame
            for (int a = 0; a < Size_first; a++) {
                for (int d = 0; d < Size_second; d++) {

                    //obtaining the distance of the nearest Bid
                    double distance_center = Math.sqrt(Math.pow(Center_j_second[d]-Center_j_first[a],2)+Math.pow(Center_i_second[d]-Center_i_first[a],2));
                     distance_min = Math.min(distance_center,distance_min);

                     //obtaining distance_i and distance_j of the nearest bid
                     if(distance_min==distance_center){
                         distance_min_i = Math.abs(Center_i_second[d]-Center_i_first[a]);
                         distance_min_j = Math.abs(Center_j_second[d]-Center_j_first[a]);
                     }
                }
                // obtaining the sum of the distance of the Bids
                if (distance_min<=25) {

                    sum_distance_i += Math.pow(distance_min_i*0.175*Math.pow(10,-6),2);
                    sum_distance_j += Math.pow(distance_min_j*0.175*Math.pow(10,-6),2);
                    Number_distance++;
                }
                distance_min_i = 640;
                distance_min_j = 640;
                distance_min = 640;
            }
            frame++;
        }
        if (frame == 9) {

            //Process frame 9 to find Bids
            String str = String.valueOf(frame);
            double[] Center_i_first = new BidFinder().getCenter_i("data/"+folder+"/frame0000" + str + ".jpg");
            double[] Center_j_first = new BidFinder().getCenter_j("data/"+folder+"/frame0000" + str + ".jpg");

            //Get the Numbers of frame Bids
            int Size_first = new BidFinder().size("data/"+folder+"/frame0000" + str + ".jpg");

            System.out.println("This is time : "+time);
            time=time-1;

            //process the next frame
            String str_next = String.valueOf(frame + 1);
            double[] Center_i_second = new BidFinder().getCenter_i("data/"+folder+"/frame000" + str_next + ".jpg");
            double[] Center_j_second = new BidFinder().getCenter_j("data/"+folder+"/frame000" + str_next + ".jpg");

            //Get the Numbers of next frame Bids
            int Size_second = new BidFinder().size("data/"+folder+"/frame000" + str_next + ".jpg");

            double distance_min_i = 640;
            double distance_min_j = 640;
            double distance_min = 640;

            //comparison of the distance between the Bids of each frame with the next frame
            for (int a = 0; a < Size_first; a++) {
                for (int d = 0; d < Size_second; d++) {

                    //obtaining the distance of the nearest Bid
                    double distance_center = Math.sqrt(Math.pow(Center_j_second[d]-Center_j_first[a],2)+Math.pow(Center_i_second[d]-Center_i_first[a],2));
                    distance_min = Math.min(distance_center,distance_min);

                    //obtaining distance_i and distance_j of the nearest bid
                    if(distance_min==distance_center){
                        distance_min_i = Math.abs(Center_i_second[d]-Center_i_first[a]);
                        distance_min_j = Math.abs(Center_j_second[d]-Center_j_first[a]);
                    }
                }
                // obtaining the sum of the distance of the Bids
                if (distance_min<=25) {
                    sum_distance_i += Math.pow(distance_min_i*0.175*Math.pow(10,-6),2);
                    sum_distance_j += Math.pow(distance_min_j*0.175*Math.pow(10,-6),2);
                    Number_distance++;
                }
                distance_min_i = 640;
                distance_min_j = 640;
                distance_min = 640;

            }
            frame++;
        }
        while (frame > 9 && frame < 99) {

            //Process image 10 to 98 to find Bids
            String str = String.valueOf(frame);
            double[] Center_i_first = new BidFinder().getCenter_i("data/"+folder+"/frame000" + str + ".jpg");
            double[] Center_j_first = new BidFinder().getCenter_j("data/"+folder+"/frame000" + str + ".jpg");

            //Get the Numbers of frame Bids
            int Size_first = new BidFinder().size("data/"+folder+"/frame000" + str + ".jpg");

            System.out.println("This is time : "+time);
            time=time-1;

            //process the next frame
            String str_next = String.valueOf(frame + 1);
            double[] Center_i_second = new BidFinder().getCenter_i("data/"+folder+"/frame000" + str_next + ".jpg");
            double[] Center_j_second = new BidFinder().getCenter_j("data/"+folder+"/frame000" + str_next + ".jpg");

            //Get the Numbers of next frame Bids
            int Size_second = new BidFinder().size("data/"+folder+"/frame000" + str_next + ".jpg");

            double distance_min_i = 640;
            double distance_min_j = 640;
            double distance_min = 640;

            //comparison of the distance between the Bids of each frame with the next frame
            for (int a = 0; a < Size_first; a++) {
                for (int d = 0; d < Size_second; d++) {

                    //obtaining the distance of the nearest Bid
                    double distance_center = Math.sqrt(Math.pow(Center_j_second[d]-Center_j_first[a],2)+Math.pow(Center_i_second[d]-Center_i_first[a],2));
                    distance_min = Math.min(distance_center,distance_min);

                    //obtaining distance_i and distance_j of the nearest bid
                    if(distance_min==distance_center){
                        distance_min_i = Math.abs(Center_i_second[d]-Center_i_first[a]);
                        distance_min_j = Math.abs(Center_j_second[d]-Center_j_first[a]);
                    }
                }
                // obtaining the sum of the distance of the Bids
                if (distance_min<=25) {
                    sum_distance_i += Math.pow(distance_min_i*0.175*Math.pow(10,-6),2);
                    sum_distance_j += Math.pow(distance_min_j*0.175*Math.pow(10,-6),2);
                    Number_distance++;
                }
                distance_min_i = 640;
                distance_min_j = 640;
                distance_min = 640;
            }

            frame++;
        }
        if (frame == 99) {

            //Process image 99 to find Bids
            String str = String.valueOf(frame);
            double[] Center_i_first = new BidFinder().getCenter_i("data/"+folder+"/frame000" + str + ".jpg");
            double[] Center_j_first = new BidFinder().getCenter_j("data/"+folder+"/frame000" + str + ".jpg");

            //Get the Numbers of frame Bids
            int Size_first = new BidFinder().size("data/"+folder+"/frame000" + str + ".jpg");

            System.out.println("This is time : "+time);
            time=time-1;

            //process the next frame
            String str_next = String.valueOf(frame + 1);
            double[] Center_i_second = new BidFinder().getCenter_i("data/"+folder+"/frame00" + str_next + ".jpg");
            double[] Center_j_second = new BidFinder().getCenter_j("data/"+folder+"/frame00" + str_next + ".jpg");

            //Get the Numbers of next frame Bids
            int Size_second = new BidFinder().size("data/"+folder+"/frame00" + str_next + ".jpg");

            double distance_min_i = 640;
            double distance_min_j = 640;
            double distance_min = 640;

            //comparison of the distance between the Bids of each frame with the next frame
            for (int a = 0; a < Size_first; a++) {
                for (int d = 0; d < Size_second; d++) {

                    //obtaining the distance of the nearest Bid
                    double distance_center = Math.sqrt(Math.pow(Center_j_second[d]-Center_j_first[a],2)+Math.pow(Center_i_second[d]-Center_i_first[a],2));
                    distance_min = Math.min(distance_center,distance_min);

                    //obtaining distance_i and distance_j of the nearest bid
                    if(distance_min==distance_center){
                        distance_min_i = Math.abs(Center_i_second[d]-Center_i_first[a]);
                        distance_min_j = Math.abs(Center_j_second[d]-Center_j_first[a]);
                    }
                }
                // obtaining the sum of the distance of the Bids
                if (distance_min<=25) {
                    sum_distance_i += Math.pow(distance_min_i*0.175*Math.pow(10,-6),2);
                    sum_distance_j += Math.pow(distance_min_j*0.175*Math.pow(10,-6),2);
                    Number_distance++;
                }
                distance_min_i = 640;
                distance_min_j = 640;
                distance_min = 640;
            }
            frame++;
        }
        while (frame > 99 && frame < 199) {

            //Process frame 100 to 198 to find Bids
            String str = String.valueOf(frame);
            double[] Center_i_first = new BidFinder().getCenter_i("data/"+folder+"/frame00" + str + ".jpg");
            double[] Center_j_first = new BidFinder().getCenter_j("data/"+folder+"/frame00" + str + ".jpg");

            //Get the Numbers of frame Bids
            int Size_first = new BidFinder().size("data/"+folder+"/frame00" + str + ".jpg");

            System.out.println("This is time : "+time);
            time=time-1;

            //process the next frame
            String str_next = String.valueOf(frame + 1);
            double[] Center_i_second = new BidFinder().getCenter_i("data/"+folder+"/frame00" + str_next + ".jpg");
            double[] Center_j_second = new BidFinder().getCenter_j("data/"+folder+"/frame00" + str_next + ".jpg");

            //Get the Numbers of next frame Bids
            int Size_second = new BidFinder().size("data/"+folder+"/frame00" + str_next + ".jpg");

            double distance_min_i = 640;
            double distance_min_j = 640;
            double distance_min = 640;

            //comparison of the distance between the Bids of each frame with the next frame
            for (int a = 0; a < Size_first; a++) {
                for (int d = 0; d < Size_second; d++) {

                    //obtaining the distance of the nearest Bid
                    double distance_center = Math.sqrt(Math.pow(Center_j_second[d]-Center_j_first[a],2)+Math.pow(Center_i_second[d]-Center_i_first[a],2));
                    distance_min = Math.min(distance_center,distance_min);

                    //obtaining distance_i and distance_j of the nearest bid
                    if(distance_min==distance_center){
                        distance_min_i = Math.abs(Center_i_second[d]-Center_i_first[a]);
                        distance_min_j = Math.abs(Center_j_second[d]-Center_j_first[a]);
                    }
                }
                // obtaining the sum of the distance of the Bids
                if (distance_min<=25) {
                    sum_distance_i += Math.pow(distance_min_i*0.175*Math.pow(10,-6),2);
                    sum_distance_j += Math.pow(distance_min_j*0.175*Math.pow(10,-6),2);
                    Number_distance++;
                }

                distance_min_i = 640;
                distance_min_j = 640;
                distance_min = 640;
            }
            frame++;
            if (frame == 199) {
                BidTracker_Number = (sum_distance_j+sum_distance_i) / (Number_distance*2);

                break;
            }
        }


        return BidTracker_Number;
    }
}

