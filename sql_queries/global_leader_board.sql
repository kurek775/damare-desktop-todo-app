select
u.user_id
,u.username
,u.user_email
,count(t.task_id) as tasks_completed_count
from devel_user u
left join devel_task t
	on u.user_id=t.task_user_id
	and t.task_finished=true
group by 1,2,3
order by 4 desc