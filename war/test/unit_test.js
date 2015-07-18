describe('Group meals', function () {
    var mealService;
    
    beforeEach(module('MealJournalApp'));
    
    beforeEach(inject(function (meals) {
        
    }));
    
    it('should be true', function () {
        expect(true).toBeTruthy();
    });
    
    /*it('expects mealService to be defined', function () {
        expect(mealService).toBeDefined();
    });
    
    /*it('should group meals by date', function () {
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
        
        var mealsArray = mealService.groupMeals(meals);
        
        expect(mealsArray).toEqual(mealsArray);
    });*/
});