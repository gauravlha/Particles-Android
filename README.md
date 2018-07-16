# Particles-Android

To get a Git project into your build:

<br />Step 1. Add the JitPack repository to your build file
  Add it in your root build.gradle at the end of repositories:

	  allprojects {
		  repositories {
			  ...
			  maven { url 'https://jitpack.io' }
		  }
	  }
    
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.gauravlha:Particles-Android:0.1.0'
	}


<br />What is Particles-Android?

<br />![Alt Text](https://media.giphy.com/media/3rV4boVJHQy6jeeU5h/giphy.gif)



<br />How to use?
<br />- Add this in your xml file:
	  
	<com.gauravlha.particles_android.CustomParticlesView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:numberOfBalls="50" />
	
<br />and you're good to go.

<br />What all you can customize?
	  
	/* You can define number of balls you want to use in the view, just don't make it more than say 400. */
	custom:numberOfBalls= "50"
	
	custom:frameRate="5" /* How fast you want to redraw the canvas. */
	
	/* Speed of the ball in x coordinate. Make the UpperLimit and LowerLimit same incase you want it to be constant, 	otherwise random movement between the range will be given. */
	custom:xVelocityLowerLimit="1"
	custom:xVelocityUpperLimit="5"
	
	/* Speed of the ball in y coordinate. Make the UpperLimit and LowerLimit same incase you want it to be constant, 	otherwise random movement between the range will be given. */
	custom:yVelocityLowerLimit="1"
	custom:yVelocityUpperLimit="5"
	
	/* To give the blurry effect to the balls. Make the UpperLimit and LowerLimit same incase you want it to be constant, 		otherwise random movement between the range will be given and incase you want neither of the balls to be blurry make 		LowerLimit & UpperLimit to 255. */
	custom:paintAlphaLowerLimit="100"
	custom:paintAlphaUpperLimit="255"
	
	/* Size of the ball. Make the UpperLimit and LowerLimit same incase you want it to be constant, otherwise random 		movement between the range will be given. */
	custom:ballSizeLowerLimit="5"
	custom:ballSizeUpperLimit="10"
	
	/* Distance you want to set between the two given balls with respect to their xCoordinate. */
	custom:lineXCoordinateDistance="100"
	
	/* Distance you want to set between the two given balls with respect to their yCoordinate. */
	custom:lineYCoordinateDistance="20"
	
	/* Circle balls Color */
	custom:circleColor="#000000"
	
	/* Color of the line joining the two balls */
	custom:lineColor="#000000"
	

<br />Currently Working on:
<br />	1. Will add few more shapes to make the library to make it much more cooler.
<br />	2. Trying out implementing different effects.
