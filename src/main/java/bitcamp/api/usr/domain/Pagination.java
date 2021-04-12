package bitcamp.api.usr.domain;

import lombok.Data;
@Data
public class Pagination {
    public final int BLOCK_SIZE = 10;
    private int totalCount;
    private int startRow;
    private int endRow;
    private int pageCount;
    private int pageSize;
    private int startPage;
    private int endPage;
    private int pageNum;
    private int blockCount;
    private int prevBlock;
    private int nextBlock;
    private int blockNum;
    private String tname;
    private boolean hasPrev;
    private boolean hasNext;

    public Pagination() {}

    public Pagination(int pageSize, int pageNum, int count) {
        this.pageSize = (pageSize==0) ? 10: pageSize;
        this.pageNum = pageNum ;
        this.totalCount = count;
        this.pageCount = (totalCount % pageSize != 0) ? totalCount / pageSize + 1 : totalCount / pageSize;
        this.blockCount = (pageCount % BLOCK_SIZE != 0) ? pageCount / BLOCK_SIZE + 1 : pageCount / BLOCK_SIZE;
        this.startRow = (pageNum - 1) * pageSize;
        this.endRow = (pageCount != pageNum) ? startRow + pageSize - 1 : totalCount - 1;
        this.blockNum = (pageNum - 1) / BLOCK_SIZE;
        this.startPage = blockNum * BLOCK_SIZE + 1;
        this.endPage = ((blockNum + 1) != blockCount) ? startPage + (BLOCK_SIZE - 1) : pageCount;
        this.hasPrev = blockNum != 0;
        this.hasNext = (blockNum + 1) != blockCount;
        this.nextBlock = startPage + BLOCK_SIZE;
        this.prevBlock = startPage - BLOCK_SIZE;
    }
}