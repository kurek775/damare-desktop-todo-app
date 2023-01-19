with all_friendships as (
select
    f.friendship_id
	,f.user_id1_requester
	,f.user_id2_requested
from devel_user u
inner join devel_friendship f
    on (u.user_id=f.user_id1_requester or u.user_id=f.user_id2_requested)
where (u.user_id=%s)
and f.friendship_status_code=1
)
select
	f.friendship_id
from all_friendships f
where (f.user_id1_requester=%s or f.user_id2_requested=%s)