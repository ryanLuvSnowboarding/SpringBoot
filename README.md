# This is web application built with React and Spring Boot for users to make online orders similar to UberEats / DoorDash. 

In the backend, I implement user signup, login, logout, get all restaurants menus and single restaurant's menus, 
add food to the cart, modify the cart, and checkout the cart functionalities.

## The backend structure and data structure are shown below: 

the backend strucure is shown below: 
In the Spring Boot, all the incoming HTTP requests are handled by controllers. Service classes focus on the business logic. For example: RestaurantService is handle requests to return the list of restaurants with all the menu imtes associated. 
<img width="725" alt="Screen Shot 2023-10-11 at 14 21 45" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/c825df57-cb38-4558-936f-affc09e81f89">
<img width="864" alt="Screen Shot 2023-10-11 at 14 24 42" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/b02d3a3f-b7e6-4544-a7b7-c7f4e845dc3c">



## The SignUp page: user can register and login to our home page 

If the client does not have an acccount yet, the client could click Register buttom at upper right corner to register for an account first, then log in to the Home page.
<img width="1351" alt="Screen Shot 2023-10-11 at 11 11 28" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/6217e719-d7d2-4655-8a07-b9e04037416f">
<img width="1344" alt="Screen Shot 2023-10-11 at 11 12 07" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/21f8566b-039f-47bb-9abc-954c9dd07eb5">

## In the Home page, user can choose the restaurant he want to order：

There is a search bar at the left displaying all available restaurants and the client could make order of dishes cross different restaurants into their shopping cart
<img width="1346" alt="Screen Shot 2023-10-11 at 11 12 52" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/da25276a-2161-4c14-8b7f-c3ba17bc5b12">
<img width="1348" alt="Screen Shot 2023-10-11 at 11 13 15" src="https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/67de92dd-0960-423d-980f-50aefb8e01a7">


## Then users can go to cart and check the food they have selected and checkout:
![image](https://github.com/ryanLuvSnowboarding/SpringBoot/assets/143637686/5d73e219-c071-41bc-97c7-80da2732edf5)
