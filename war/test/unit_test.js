describe('Group meals', function () {
    var mealService = null;

    beforeEach(module('MealJournalApp'));

    beforeEach(angular.mock.inject(function (meals) {
        mealService = meals;
    }));

    it('expects meals service to be defined', function () {
        expect(mealService).toBeTruthy();
    });

    it('should group meals by date', function () {
        var meals = [
            {
                "name": "Rice",
                "unit": "cup",
                "calories": 205,
                "iconUrl": "meals-icons/rice.jpg",
                "dateCreated": 1436154582626,
                "quantity": 1
            }
        ];

        var date = new Date(1436154582626);
        var temp = new Date(date.getFullYear(), date.getMonth(), date.getDate()).getTime().toString();
        
        var expectedGroupedMeals = {};
        expectedGroupedMeals[temp] = [
            {
                "name": "Rice",
                "unit": "cup",
                "calories": 205,
                "iconUrl": "meals-icons/rice.jpg",
                "dateCreated": 1436154582626,
                "quantity": 1
            }
        ];
        expectedGroupedMeals[temp].totalCalories = 205;

        var groupedMeals = mealService.groupMeals(meals);

        expect(groupedMeals).toEqual(expectedGroupedMeals);
    });
});