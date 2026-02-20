select
    user_id,
    product_id
from ONLINE_SALE
group by user_id, product_id
having count(*) >= 2
order by USER_ID, PRODUCT_ID desc

# select user_id, product_id, count(*) from online_sale
# group  by user_id, product_id having count(*) >= 2