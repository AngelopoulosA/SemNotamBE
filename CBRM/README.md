# Flora-2 Implementation for Contextualized Business Rule Management

## Environment setup
The folder ./Contexts/ needs to be available in the Flora-2 directory, in our use case under folder *OO/*.

Tested with Flora-2 reasoner 1.2 of 2017-01-30 (rev: 1258b) and XSB Version 3.7.0 (Clan MacGregor) of 2017-03-15 (linux-gnu,x64).

## Description
*ctxModel.flr* contains the generic model, *ctxModelAIM.flr* a domain- and application-specific model. *bc.flr* contains NOTAMs and interest specifications as well as bc1 and other SemNOTAMCases. The paper assumes that one NOTAM and one interest specification are in module *bc*. This implementation does not so, thus the rules include restrictions to account for that. Nevertheless, only one business case at a time may reside at module *bc*.

The folder *Contexts/* comprises the different contexts and their rules/terms. The folder *tests/* contains tests of the different basic modification operations and a few composed modification operations (without notifications). The test *testAll.flr* runs all tests contained in *tests/*. Therefore, load the *testAll.flr* using **['OO/tests/TestAll.flr'>>tests].**. Any results of test are written to the file *test.log*.

Modification operations on Context Classes and Business Case Classes are considered future work.