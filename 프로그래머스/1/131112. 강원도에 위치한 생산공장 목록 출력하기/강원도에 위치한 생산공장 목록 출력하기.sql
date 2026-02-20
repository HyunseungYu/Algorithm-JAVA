select
    FACTORY_ID,
    FACTORY_NAME,
    ADDRESS
from FOOD_FACTORY
where LEFT(ADDRESS, 3) = '강원도';