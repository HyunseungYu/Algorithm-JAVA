select
    USER_ID,
    PRODUCT_ID
from ONLINE_SALE
group by USER_ID, PRODUCT_ID
having 1 < count(PRODUCT_ID)
order by USER_ID, product_id desc

# select
#     *
# from ONLINE_SALE
# order by USER_ID, product_id;