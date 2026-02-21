select
    f1.id,
    name.fish_name,
    f1.length
from fish_info f1
JOIN (
    select
        fish_type,
        max(length) as max_length
    from fish_info
    group by fish_type
) mx
    ON f1.fish_type = mx.fish_type
    AND f1.length = mx.max_length
join fish_name_info name
    on name.fish_type = f1.fish_type