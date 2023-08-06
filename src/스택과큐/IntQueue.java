package 스택과큐;

/**
 * 링버퍼란?? 배열의 처음과 끝이 연결된 자료구조
 */
public class IntQueue {
    int front; //맨 처음 요소의 인덱스
    int rear; //맨 끝 요소 다음의 인덱스(다음 요소를 넣을 위치를 미리 지정)
    int max; //큐의 용량
    int num; //큐에 저장된 요소의 수
    int[] que; //큐 본체

    //실행 시 예외 : 큐가 비어있음
    public class EmptyIntQueueException extends RuntimeException {
        public EmptyIntQueueException() {}
    }

    //실행 시 예외 : 큐가 가득 참
    public class OverflowIntQueueException extends RuntimeException {
        public OverflowIntQueueException() {}
    }

    public IntQueue(int capacity) {
        num = front = rear = 0;
        max = capacity;

        que = new int[max];
    }

    //큐에 데이터 삽입
    public void enque(int data) throws OverflowIntQueueException {
        if(num >= max)
            throw new OverflowIntQueueException();

        //1. 큐에 데이터 저장
        //2. 맨 끝 요소 다음의 인덱스 증가(다음 데이터 넣을 큐의 인덱스
        que[rear++] = data;
        num++;

        //맨 끝 요소 다음의 인덱스가 max 인 경우 큐의 처음 공간으로 인덱스 변경
        if(rear == max)
            rear = 0;
    }

    //큐에서 데이터 추출
    public int deque() throws EmptyIntQueueException {
        if(num <= 0)
            throw new EmptyIntQueueException();

        int data = que[front++];
        num--;

        if(front == max)
            front = 0;

        return data;
    }

    //큐에서 가장 첫번째 들어있는 데이터 출력
    public int peek() throws EmptyIntQueueException {
        if(num <= 0)
            throw new EmptyIntQueueException();
        return que[front];
    }

    //큐에서 데이터를 검색해 인덱스 반환
    public int indexOf(int data) {
        for(int i = 0; i < num; i++) {
            int idx = (i + front) % max;
            if(que[idx] == data)
                return que[idx];
        }
        return -1;
    }

    public void clear() {
        num = front = rear = 0;
    }

    public int capacity() {
        return max;
    }

    public int size() {
        return num;
    }

    public boolean isEmpty() {
        return num <= 0;
    }

    public boolean isFull() {
        return num >= max;
    }

    public void dump() {
        if(num <= 0)
            System.out.println("큐가 비어 있습니다.");
        else {
            for(int i = 0; i < num; i++) {
                System.out.print(que[(front + i) % max] + " ");
            }
            System.out.println();
        }
    }
}
