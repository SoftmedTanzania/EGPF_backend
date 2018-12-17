package org.opensrp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
	public class PKReferralServiceIndicator implements Serializable {
	public static final String COL_SERVICE_ID = "service_id";

	public static final String COL_INDICATOR_ID = "indicator_id";

		@Column(name = COL_SERVICE_ID)
		private long serviceId;

		@Column(name = COL_INDICATOR_ID)
		private long indicatorId;


		public long getServiceId() {
			return serviceId;
		}

		public void setServiceId(long serviceId) {
			this.serviceId = serviceId;
		}

		public long getIndicatorId() {
			return indicatorId;
		}

		public void setIndicatorId(long indicatorId) {
			this.indicatorId = indicatorId;
		}

		public PKReferralServiceIndicator(long indicatorId, long serviceId) {
			this.serviceId = serviceId;
			this.indicatorId = indicatorId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof PKReferralServiceIndicator)) return false;
			PKReferralServiceIndicator that = (PKReferralServiceIndicator) o;
			return Objects.equals(getIndicatorId(), that.getIndicatorId()) &&
					Objects.equals(getServiceId(), that.getServiceId());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getIndicatorId(), getServiceId());
		}
	}