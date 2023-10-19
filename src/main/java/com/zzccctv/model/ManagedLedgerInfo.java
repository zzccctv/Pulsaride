package com.zzccctv.model;

import java.util.List;
import java.util.Map;

public class ManagedLedgerInfo {
    public long version;
    public String creationDate;
    public String modificationDate;

    public List<LedgerInfo> ledgers;
    public PositionInfo terminatedPosition;

    public Map<String, CursorInfo> cursors;

    public Map<String, String> properties;

    public static class LedgerInfo {
        public long ledgerId;
        public Long entries;
        public Long size;
        public Long timestamp;
        public boolean isOffloaded;
    }

    public static class CursorInfo {
        /** Z-Node version. */
        public long version;
        public String creationDate;
        public String modificationDate;

        // If the ledger id is -1, then the mark-delete position is
        // the one from the (ledgerId, entryId) snapshot below
        public long cursorsLedgerId;

        // Last snapshot of the mark-delete position
        public PositionInfo markDelete;
        public List<MessageRangeInfo> individualDeletedMessages;
        public Map<String, Long> properties;
    }

    public static class PositionInfo {
        public long ledgerId;
        public long entryId;

        @Override
        public String toString() {
            return String.format("%d:%d", ledgerId, entryId);
        }
    }

    public static class MessageRangeInfo {
        // Starting of the range (not included)
        public PositionInfo from = new PositionInfo();

        // End of the range (included)
        public PositionInfo to = new PositionInfo();

        @Override
        public String toString() {
            return String.format("(%s, %s]", from, to);
        }
    }
}
