select
	 u.user_id
	,u.username
	,fu.user_id as friends_id
	,fu.username as friends_name

from devel_user u
left join devel_friendship f
	on (u.user_id=f.user_id1_requester 
	or u.user_id=f.user_id2_requested)
left join devel_user fu
	on (f.user_id1_requester=fu.user_id 
	or f.user_id2_requested=fu.user_id)
	and u.user_id<>fu.user_id
	
where friendship_status_code=1 --"accepted"
