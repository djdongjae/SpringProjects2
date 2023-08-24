package hello.core_202308231239i.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
