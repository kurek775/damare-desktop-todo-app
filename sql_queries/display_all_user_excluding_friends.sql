with friends as (
select 
    fu.user_id
from devel_user u 
inner join devel_friendship f 
    on (u.user_id=f.user_id1_requester or u.user_id=f.user_id2_requested) 
inner join devel_user fu 
    on (fu.user_id=f.user_id1_requester or fu.user_id=f.user_id2_requested) 
where (u.user_id=%s) 
and fu.user_id<>u.user_id
)
select *
from devel_user u
left join friends f
	on u.user_id=f.user_id
	where f.user_id is null
	and u.user_id<>%s