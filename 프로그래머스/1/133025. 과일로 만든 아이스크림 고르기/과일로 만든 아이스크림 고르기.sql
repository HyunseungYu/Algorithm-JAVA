select
    f.flavor
from FIRST_HALF f
join ICECREAM_INFO i on f.flavor = i.flavor
where 3000 < f.TOTAL_ORDER and i.INGREDIENT_TYPE = 'fruit_based';

# select
#     *
# from FIRST_HALF f
# join ICECREAM_INFO i on f.flavor = i.flavor