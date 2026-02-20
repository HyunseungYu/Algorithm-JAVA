# select
#     info.REST_ID,
#     REST_NAME,
#     FOOD_TYPE,
#     FAVORITES,
#     ADDRESS,
#     ROUND(AVG(review.REVIEW_SCORE)) as SCORE
# from REST_INFO info
# join REST_REVIEW review on info.REST_ID = review.REST_ID
# group by info.REST_ID
# where LEFT(info.ADDRESS, 2) = '서울';

select
    info.REST_ID,
    REST_NAME,
    FOOD_TYPE,
    FAVORITES,
    ADDRESS,
    ROUND(AVG(review.REVIEW_SCORE), 2) as SCORE
from REST_INFO info
join REST_REVIEW review on info.REST_ID = review.REST_ID
group by info.REST_ID
having LEFT(info.ADDRESS, 2) = '서울'
order by AVG(REVIEW_SCORE) desc, FAVORITES desc;
