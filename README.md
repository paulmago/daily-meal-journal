# Daily Meal Journal
The goal of this project is to create a Meal Journal for users who wish to keep track of their calorie intake. For the system to work, a list of commonly consumed food and/or beverage with their specific calorie count is necessary. After which, the user may select which he/she wishes to consume for the day.

## Meal Management Screen
### Functions
- Add Meal Information
  - Meal Name
  - Quantity w/ unit (Quantity refers to the default serving size)
  - Calorie count
- Edit Meal Information
- Delete Meal Information
- List Meal
- Search Meal by Name
- Display Meal Information

### Validations
- If calorie count is not valid, return an error message
- If quantity is not numerical, return an error message
- If no unit is selected, return an error message
- If meal is already added, return an error message

## Meal Journal Screen
### Functions
- Search for a meal by name
- Display search result
- Add meal to list for current date
- Enter quantity of meal
- Display list of consumed meals grouped according to date
- Remove meal from list
- Edit quantity of meal
- Compute total calories
- Display total calories

### Validations
- If quantity is not valid, return an error message
- If the searched item is not found, return an error message
- If number of meals per day exceeds 10, return an error message
- If calorie count exceeds 2000, return an error message
