package helper;


import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;

public class Utils {
    static double SCROLL_RATIO = 0.5;

    protected static void swipe(Point start, Point end, Duration duration) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(input, 0);
        sequence.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        sequence.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        sequence.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        AppDriver.getDriver().perform(ImmutableList.of(sequence));
    }
	
	
	/*
	 * 
	 * If scrollRatio = 0.8 then page will scroll more
	 * If scrollRatio = 0.2 then page will scroll very less
	 * 
	 * If user want to scroll page in DOWN direction
	 * Then scroll mobile screen starting from Bottom to Top
	 * 
	 * If user want to scroll page in RIGHT direction
	 * Then scroll mobile screen starting from Right to Left
	 * 
	 * Assume Screen size = 50(x value) by 100(y value) 
	 * midpoint of screen will be 50*0.5 & 100*0.5 i.e. (25,50)
	 * 
	 */
	public static void scroll(String pageDirection, double scrollRatio){
        Duration SCROLL_DUR = Duration.ofMillis(300);
        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll Ratio must be between 0 and 1");
        }

        Dimension size = AppDriver.getDriver().manage().window().getSize();
        System.out.println("Screen Size = "+size);
        Point midPoint = new Point((int)(size.width * 0.5),(int)(size.height * 0.5));
        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio); // 50 + 25 
        int top = midPoint.y - (int)(midPoint.y * scrollRatio); // 50 - 25
        int left = midPoint.x - (int)(midPoint.x * scrollRatio); // 25 - 12.5
        int right = midPoint.x + (int)(midPoint.x * scrollRatio); // 25 + 12.5

        
        if (pageDirection == "UP") {
        	//Swipe Top to bottom, Page will go UP
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
        } else if (pageDirection == "DOWN") {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
        } else if (pageDirection == "LEFT") {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
        } else {
        	//RIGHT
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
        }
    }
	
	// This is used to scroll downwards untill the elementToFind is found
	public static void scrollDownTill(By elementToFind) throws InterruptedException {
		int size = 0;
		
		while(size == 0) {
			System.out.println("Element Not Found ... Keep Scrolling");
			Utils.scroll("DOWN", 0.5);
			Thread.sleep(2000L);
			size = AppDriver.getDriver().findElements(elementToFind).size();
			if(size == 1) {
				System.out.println("Found Element");
				size++;
				break;				
			}
		}
	}
	
	// This is used to scroll upwards untill the elementToFind is found
	public static void scrollUpTill(By elementToFind) throws InterruptedException {
	    int size = 0;

	    while (size == 0) {
	        System.out.println("Element Not Found ... Keep Scrolling Up");
	        Utils.scroll("UP", 0.3);
	        Thread.sleep(2000L);
	        size = AppDriver.getDriver().findElements(elementToFind).size();
	        if (size == 1) {
	            System.out.println("Found Element");
	            break;
	        }
	    }
	}

}
