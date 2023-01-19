select
    fu.user_id
    ,fu.username
    ,fu.user_email
    ,fu.user_passwd
from devel_user u
inner join devel_friendship f
    on (u.user_id=f.user_id1_requester or u.user_id=f.user_id2_requested)
inner join devel_user fu
    on (fu.user_id=f.user_id1_requester or fu.user_id=f.user_id2_requested)
where (u.user_id=%s)
and f.friendship_status_code=1 --accepted
and fu.user_id<>u.user_id