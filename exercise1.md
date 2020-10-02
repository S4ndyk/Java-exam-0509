
You have the folling interface at your use. Add it to the exercise template.

```
public interface Shape{
    
    /**
     * Returns the surface area of the shape, i.e the combined surface area of each side
     */ 
    int getArea();
    
    /**
     * Returns the volume of the shape
     */ 
    int getVolume();
}
```
Create the class `Cube` that implements the interface `Shape`. The class must have a constructor with an int-type parameter.
The given parameter represents the length of one side of the cube.