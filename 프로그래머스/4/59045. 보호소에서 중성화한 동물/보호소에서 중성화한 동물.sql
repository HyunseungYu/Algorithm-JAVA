select
    o.animal_id,
    o.ANIMAL_TYPE,
    o.NAME
from ANIMAL_OUTS o
join ANIMAL_INS i on o.animal_id = i.animal_id
where i.SEX_UPON_INTAKE like '%intact%' and (o.SEX_UPON_OUTCOME like '%Spayed%' or o.SEX_UPON_OUTCOME like '%neutered%');