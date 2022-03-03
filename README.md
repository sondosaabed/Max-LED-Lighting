# Max LED Lighting
This is a Java desktop application. Two circuit boards (S and L), where S contains n power sources, while L contains LEDs n are given. The source S sorted in ascending order <1, 2, 3, … , n>, while the LEDs on L are not <2, 9, 5, 14, 3...>. The app connects each LED in L to its pair in S (i.e., 1 with 1, 2 with 2) through unshielded wires, thus when a wire connects a LED (li) in L with its corresponding source in S (si), it may prevent other LEDs from being connected (no two wires may cross).

Example as below, if you connected l1 with s1, then 3 LEDs maximum will be lightened.
![00](https://user-images.githubusercontent.com/65151701/156570554-54e7f1f9-93fd-4c83-82cb-e71031e22366.png)

In this project I used JavaFX For User Graphical inteface. Applied Longest Common Subsequence algorithm as a dynamic solution. This project is built as an assignment for Algorithm course.

# Running the project 
In order to run the project, needs the JavaFx library to be build on the projecet path. 
The input of the program is: 
n (number of LEDs, say 100).
A permutation of numbers (<1 to n>) :the ordering of the LEDs on the board L. 

# File format example
![0](https://user-images.githubusercontent.com/65151701/156570751-0646ca2b-66fd-4a99-88fd-5de3b9771994.png)

# Programms versions
javafx.version=17.0.1,  
Java.version=17.0.8

# Screenshots of Application
Browse for input file:
![1](https://user-images.githubusercontent.com/65151701/156570869-06ffae91-885d-41b4-b41e-851a0e158140.png)

Ready to run app:
![2](https://user-images.githubusercontent.com/65151701/156570961-349ba63c-8cb6-4037-a9cc-66316c726fb2.png)

Number of LEDs:
![3](https://user-images.githubusercontent.com/65151701/156571079-e049f09f-aebb-4c53-bf45-8880cc33a1a6.png)

Show board of LEDs:
![4](https://user-images.githubusercontent.com/65151701/156571177-97585c13-4b27-4224-8b6d-81124e6bf845.png)

Connect the LEDS based on Maximum: 
![5](https://user-images.githubusercontent.com/65151701/156571349-6d31e960-1fca-4388-ac6c-a95d0ea988d1.png)

Dynamic table to get the optimal solution:
![6](https://user-images.githubusercontent.com/65151701/156571637-3db39bce-ca67-4399-8575-1702bfb71d53.png)

Another input for 10 LEDs:
![7](https://user-images.githubusercontent.com/65151701/156571855-de45d01a-c544-4624-8904-25d150751b82.png)

Dynamic table for 10 LEDs:
![8](https://user-images.githubusercontent.com/65151701/156571859-e499fb4b-916c-42a3-beb4-744ad3084388.png)
