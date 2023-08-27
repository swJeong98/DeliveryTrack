# DeliveryTrack
Client-Server GUI Program (Delivery Management)

## INTRO

---
**GUI 기반 클라이언트 / 서버 택배 추적 관리**
관리자 인증번호로 로그인하고, 자신의 택배 현황을 관리할 수 있습니다.

**‘상품 추가’ 버튼을 눌러 새로운 택배를 추가**할 수 있습니다.
![Untitled.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled.png)
![Untitled 1.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled%201.png)

원하는 택배를 선택하고, ‘수정’ 또는 ‘삭제’ 버튼을 통해 수정, 삭제할 수 있습니다.
![Untitled 2.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled%202.png)
![Untitled 3.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled%203.png)


동시에 여러 관리자가 택배 관리 프로그램을 이용할 수 있습니다.
![Untitled 4.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled%204.png)

## 프로젝트 목표
1. 클라이언트 / 서버의 **소켓 기반 네트워크 통신**
    - IP / Port를 이용한 TCP/IP 커넥션 기반 통신

2. **동시에 여러 요청을 처리할 수 있는 Multi-thread 서버** 구현
    - Thread Pool 을 이용
    - 요청이 오는 때에 맞춰 기다리지 않고 처리하는 **Non-blocking Server**

3. **HTTP API** 를 통한 클라이언트와 서버 간 통신 모방
    - Socket 통신으로 실제 HTTP를 이용하지 않지만, HTTP 메시지 형식으로 통신
    - HTTP Method, Message Body(Json) 을 활용해 서버, 클라이언트 간 통신 구현

## 아키텍처
![Untitled 5.png](..%2F..%2F..%2F..%2F..%2FDownloads%2F63bdf6bd-39c4-4948-ab1c-fc22bcbe0b9c_Export-8d02b13c-fc40-4311-8583-74a61dba7f39%2FTrackDelivery%20a0af61f05f5d4e2c9869bc978fb6a35c%2FUntitled%205.png)