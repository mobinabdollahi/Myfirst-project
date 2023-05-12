import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

public class BidFinder {
private int Number_Bid;
//Get the number of Bids to know the size of the center array
public int size(String path) {
     Number_Bid = 0;
    try {

        boolean Sum_allow = false;
        boolean End_Bid = false;
        boolean single = false;
        int pixel_column = 0,first_column=0,second_column=0,rang_left=0,rang_up=0,pixel_Last_column=0,sum_pixels=0;

        Color rgb_left = new Color(0);
        Color rgb_up = new Color(0);

        BufferedImage aks = ImageIO.read(new File(path));

        // process all pixels
        for (int i = 0; i < 640; i++) {
            for (int j = 0; j < 480; j++) {

                int rang = aks.getRGB(i, j);
                Color rgb = new Color(rang);
                if (j == 479) {
                    break;
                }
                int rang_down = aks.getRGB(i, j + 1);
                Color rgb_down = new Color(rang_down);
                if (i > 0) {
                    rang_left = aks.getRGB(i - 1, j);
                    rgb_left = new Color(rang_left);
                }
                if (i == 639) {
                    break;
                }
                int rang_right = aks.getRGB(i + 1, j);
                Color rgb_right = new Color(rang_right);
                if (j > 0) {
                    rang_up = aks.getRGB(i, j - 1);
                    rgb_up = new Color(rang_up);
                }

                //if a pixel is white:
                if (rgb.getRed() >= 180 && rgb.getGreen() >= 180 && rgb.getBlue() >= 180) {

                    //if the left pixel is white
                    if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180) {
                        Sum_allow = true;
                    }
                    //if the left pixel is white and the right,top and bottom pixels are black
                    /********************************************1***************************/
                    if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180 && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                        pixel_column++;
                        pixel_Last_column++;
                        single = true;
                    }
                    //if the upper pixel is white and the lower pixel is black
                    else if (rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180)) {
                        pixel_column++;
                    }
                    //if the right pixel is white and the left,top and bottom pixels are black
                    /**************************************2**********************************/
                    else if (rgb_right.getRed() >= 180 && rgb_right.getGreen() >= 180 && rgb_right.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_left.getRed() < 180 || rgb_left.getGreen() < 180 || rgb_left.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                        pixel_column++;
                    }
                    //if the bottom pixel is white
                    else if (rgb_down.getRed() >= 180 && rgb_down.getGreen() >= 180 && rgb_down.getBlue() >= 180) {
                        pixel_column++;
                    }
                    //if the left pixel is white and the right pixel is white
                    else if ((rgb_right.getRed() >= 180 && rgb_right.getGreen() >= 180 && rgb_right.getBlue() >= 180) && (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180)){
                        pixel_column++;
                    }
                    //if the right pixel is black
                    if (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) {
                        if (!single) {
                            pixel_Last_column++;
                        }
                    }
                    //if the bottom pixel is black
                    if (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) {

                        //get the sum of the pixels of the columns
                        if (pixel_column > 0) {

                            second_column = pixel_column;
                            if (Sum_allow) {

                                first_column = first_column + second_column;
                                sum_pixels = first_column;

                            }
                        }
                        //if the left column is black
                        if (!Sum_allow) {
                            first_column = pixel_column;
                        }
                        // if the last column of Bid is reached
                        if (pixel_Last_column == pixel_column) {
                            End_Bid = true;
                        }
                        pixel_Last_column = 0;
                        pixel_column = 0;
                    }
                    //if the right pixel and the bottom pixel are black
                    if ((rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180)) {

                        //if the left pixel or the top pixel is white
                        if ((rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180) || (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180)) {

                            //if the last pixel is reached
                            if (End_Bid) {
                                if (sum_pixels >= 25) {
                                    Number_Bid++;
                                    //clear
                                    pixel_column = 0;
                                    first_column = 0;
                                    pixel_Last_column = 0;
                                    End_Bid = false;
                                    Sum_allow = false;
                                    single = false;
                                }
                                if (sum_pixels < 25) {
                                    //clear
                                    pixel_column = 0;
                                    first_column = 0;
                                    pixel_Last_column = 0;
                                    End_Bid = false;
                                    Sum_allow = false;
                                    single = false;
                                }
                            }
                        }
                    } else {
                        End_Bid = false;
                    }
                }
                }
            }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return Number_Bid;
}

private double[] center_i;

//obtain the i of the center of the Bids
public double[] getCenter_i(String path){
    center_i=new  double[size(path)];
    try {
        boolean Sum_allow=false , End_Bid=false , single=false;

    int pixel_column = 0,first_column=0,second_column=0,rang_left=0,rang_up=0,pixel_Last_column=0,sum_pixels=0,i_sum=0,array=0;

    double i_center=0;

    Color rgb_left = new Color(0);
    Color rgb_up = new Color(0);

    BufferedImage aks = ImageIO.read(new File(path));

    // process all pixels
    for (int i = 0; i < 640; i++) {
        for (int j = 0; j < 480; j++) {

            int rang = aks.getRGB(i, j);
            Color rgb = new Color(rang);
            if (j == 479) {
                break;
            }
            int rang_down = aks.getRGB(i, j + 1);
            Color rgb_down = new Color(rang_down);
            if (i > 0) {
                rang_left = aks.getRGB(i - 1, j);
                rgb_left = new Color(rang_left);
            }
            if (i == 639) {
                break;
            }
            int rang_right = aks.getRGB(i + 1, j);
            Color rgb_right = new Color(rang_right);
            if (j > 0) {
                rang_up = aks.getRGB(i, j - 1);
                rgb_up = new Color(rang_up);
            }
            //if a pixel is white:
            if (rgb.getRed() >= 180 && rgb.getGreen() >= 180 && rgb.getBlue() >= 180) {

                //if the left pixel is white
                if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180) {
                    Sum_allow = true;
                }
                //if the left pixel is white and the right,top and bottom pixels are black
                /********************************************1***************************/
                if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180 && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                    pixel_column++;
                    pixel_Last_column++;
                    single = true;
                    //get the sum of all i
                    i_sum = i_sum + i;
                }
                //if the upper pixel is white and the lower pixel is black
                else if (rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180)) {
                    pixel_column++;
                    //get the sum of all i
                    i_sum = i_sum + i;
                }
                //if the right pixel is white and the left,top and bottom pixels are black
                /**************************************2**********************************/
                else if (rgb_right.getRed() >= 180 && rgb_right.getGreen() >= 180 && rgb_right.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_left.getRed() < 180 || rgb_left.getGreen() < 180 || rgb_left.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                    pixel_column++;
                    //get the sum of all i
                    i_sum = i_sum + i;
                }
                //if the bottom pixel is white
                else if (rgb_down.getRed() >= 180 && rgb_down.getGreen() >= 180 && rgb_down.getBlue() >= 180) {
                    pixel_column++;
                    //get the sum of all i
                    i_sum = i_sum + i;

                }
                //if the right pixel is black
                if (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) {
                    if (!single) {
                        pixel_Last_column++;
                    }
                }
                //if the bottom pixel is black
                if (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) {

                    //get the sum of the pixels of the columns
                    if (pixel_column > 0) {

                        second_column = pixel_column;
                        if (Sum_allow) {

                            first_column = first_column + second_column;

                            sum_pixels = first_column;

                        }
                    }
                    //if the left column is black
                    if (!Sum_allow) {
                        first_column = pixel_column;
                    }
                    // if the last column of Bid is reached
                    if (pixel_Last_column == pixel_column) {
                        End_Bid = true;
                    }
                    pixel_column = 0;
                    pixel_Last_column = 0;
                }
                //if the left pixel and the bottom pixel are black
                if ((rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180)) {

                    //if the left pixel or the top pixel is white
                    if ((rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180) || (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180)) {

                        //if the last pixel is reached
                        if (End_Bid) {
                            if (sum_pixels >= 25) {

                                //get the average of all i
                                i_center = (double) i_sum /sum_pixels;

                                //store the i in the center array
                                center_i[array]=i_center;
                                array++;

                                //clear
                                pixel_column = 0;
                                first_column = 0;
                                pixel_Last_column = 0;
                                End_Bid = false;
                                Sum_allow = false;
                                single = false;
                                i_sum=0;
                            }
                            if (sum_pixels < 25) {

                                //clear
                                pixel_column = 0;
                                first_column = 0;
                                pixel_Last_column = 0;
                                End_Bid = false;
                                Sum_allow = false;
                                single = false;
                                i_sum=0;
                            }
                        }
                    }
                } else {
                    End_Bid = false;
                }
            }
        }
    }
} catch (IOException e) {
        e.printStackTrace();
    }
return center_i;
}

    private double[] center_j;

    //obtain the j of the center of the Bids
    public double[] getCenter_j(String path){
        center_j=new  double[size(path)];
        try {
            boolean Sum_allow=false , End_Bid=false , single=false;

            int pixel_column = 0,first_column=0,second_column=0,rang_left=0,rang_up=0,pixel_Last_column=0,sum_pixels=0,j_all=0,array=0;

            double j_center=0;

            Color rgb_left = new Color(0);
            Color rgb_up = new Color(0);

            BufferedImage aks = ImageIO.read(new File(path));

            // process all pixels
            for (int i = 0; i < 640; i++) {
                for (int j = 0; j < 480; j++) {

                    int rang = aks.getRGB(i, j);
                    Color rgb = new Color(rang);
                    if (j == 479) {
                        break;
                    }
                    int rang_down = aks.getRGB(i, j + 1);
                    Color rgb_down = new Color(rang_down);
                    if (i > 0) {
                        rang_left = aks.getRGB(i - 1, j);
                        rgb_left = new Color(rang_left);
                    }
                    if (i == 639) {
                        break;
                    }
                    int rang_right = aks.getRGB(i + 1, j);
                    Color rgb_right = new Color(rang_right);
                    if (j > 0) {
                        rang_up = aks.getRGB(i, j - 1);
                        rgb_up = new Color(rang_up);
                    }
                    //if a pixel is white:
                    if (rgb.getRed() >= 180 && rgb.getGreen() >= 180 && rgb.getBlue() >= 180) {


                        //if the left pixel is white
                        if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180) {
                            Sum_allow = true;
                        }
                        //if the left pixel is white and the right,top and bottom pixels are black
                        /********************************************1***************************/
                        if (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180 && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                            pixel_column++;
                            pixel_Last_column++;
                            single = true;
                            //get the sum of all j
                            j_all = j_all + j;
                        }
                        //if the upper pixel is white and the lower pixel is black
                        else if (rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180)) {
                            pixel_column++;
                            //get the sum of all j
                            j_all = j_all + j;
                        }
                        //if the right pixel is white and the left,top and bottom pixels are black
                        /**************************************2**********************************/
                        else if (rgb_right.getRed() >= 180 && rgb_right.getGreen() >= 180 && rgb_right.getBlue() >= 180 && (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_left.getRed() < 180 || rgb_left.getGreen() < 180 || rgb_left.getBlue() < 180) && (rgb_up.getRed() < 180 || rgb_up.getGreen() < 180 || rgb_up.getBlue() < 180)) {
                            pixel_column++;
                            //get the sum of all j
                            j_all = j_all + j;
                        }
                        //if the bottom pixel is white
                        else if (rgb_down.getRed() >= 180 && rgb_down.getGreen() >= 180 && rgb_down.getBlue() >= 180) {
                            pixel_column++;
                            //get the sum of all j
                            j_all = j_all + j;
                        }
                        //if the right pixel is black
                        if (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180) {
                            if (!single) {
                                pixel_Last_column++;
                            }
                        }
                        //if the bottom pixel is black
                        if (rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) {
                            //get the sum of the pixels of the columns
                            if (pixel_column > 0) {

                                second_column = pixel_column;
                                if (Sum_allow) {

                                    first_column = first_column + second_column;
                                    sum_pixels = first_column;
                                }
                            }
                            //if the left column is black
                            if (!Sum_allow) {
                                first_column = pixel_column;
                            }
                            // if the last column of Bid is reached
                            if (pixel_Last_column == pixel_column) {
                                End_Bid = true;
                            }
                            pixel_column = 0;
                            pixel_Last_column = 0;
                        }
                        //if the left pixel and the bottom pixel are black
                        if ((rgb_down.getRed() < 180 || rgb_down.getGreen() < 180 || rgb_down.getBlue() < 180) && (rgb_right.getRed() < 180 || rgb_right.getGreen() < 180 || rgb_right.getBlue() < 180)) {

                            //if the left pixel or the top pixel is white
                            if ((rgb_up.getRed() >= 180 && rgb_up.getGreen() >= 180 && rgb_up.getBlue() >= 180) || (rgb_left.getRed() >= 180 && rgb_left.getGreen() >= 180 && rgb_left.getBlue() >= 180)) {

                                //if the last pixel is reached
                                if (End_Bid) {
                                    if (sum_pixels >= 25) {

                                        //get the average of all j
                                        j_center = (double) j_all /sum_pixels;

                                        //store the j in the center array
                                        center_j[array]=j_center;
                                        array++;

                                        //clear
                                        pixel_column = 0;
                                        first_column = 0;
                                        pixel_Last_column = 0;
                                        End_Bid = false;
                                        Sum_allow = false;
                                        single = false;
                                        j_all=0;
                                    }
                                    if (sum_pixels < 25) {

                                        //clear
                                        pixel_column = 0;
                                        first_column = 0;
                                        pixel_Last_column = 0;
                                        End_Bid = false;
                                        Sum_allow = false;
                                        single = false;
                                        j_all=0;
                                    }
                                }
                            }
                        } else {
                            End_Bid = false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return center_j;
    }
}
