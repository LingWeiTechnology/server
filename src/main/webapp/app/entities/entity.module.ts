import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BackendFarmMySuffixModule } from './farm-my-suffix/farm-my-suffix.module';
import { BackendTuanInfoMySuffixModule } from './tuan-info-my-suffix/tuan-info-my-suffix.module';
import { BackendTuanMemberMySuffixModule } from './tuan-member-my-suffix/tuan-member-my-suffix.module';
import { BackendDaySechudlerInfoMySuffixModule } from './day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.module';
import { BackendMessageMySuffixModule } from './message-my-suffix/message-my-suffix.module';
import { BackendMessageCommentMySuffixModule } from './message-comment-my-suffix/message-comment-my-suffix.module';
import { BackendEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { BackendJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { BackendJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BackendFarmMySuffixModule,
        BackendTuanInfoMySuffixModule,
        BackendTuanMemberMySuffixModule,
        BackendDaySechudlerInfoMySuffixModule,
        BackendMessageMySuffixModule,
        BackendMessageCommentMySuffixModule,
        BackendEmployeeMySuffixModule,
        BackendJobMySuffixModule,
        BackendJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendEntityModule {}
