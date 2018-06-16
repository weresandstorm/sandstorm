/**
 * The package name 'app' means the Application Layer rather than a application program.
 * <p/>
 * The application layer is responsible for driving the workflow of the application, matching the use cases at hand.
 * These operations are interface-independent and can be both synchronous or message-driven. This layer is well suited for
 * spanning transactions, high-level logging and security. The application layer is thin in terms of domain logic - it
 * merely coordinates the domain layer objects to perform the actual work.
 * <p/>
 * For more details, please see http://dddsample.sourceforge.net/architecture.html
 */
package io.sandstorm.controller.app;