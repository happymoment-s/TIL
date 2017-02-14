# “파일 이름이 너무 깁니다.” 메시지와 함께 파일 삭제 및 변경 안되는 문제

## 해결방법
cmd 실행문구
```cmd
> mkdir tmp_dir
> robocopy tmp_dir {지울 폴더명} /s /mir
> rmdir tmp_dir
> rmdir {지울 폴더명}
```

## 참고사이트
[how-to-delete-a-file-in-windows-with-a-too-long-filename](http://superuser.com/questions/45697/how-to-delete-a-file-in-windows-with-a-too-long-filename)
