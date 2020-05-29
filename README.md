# Retropia [![Matrix]][matrix-community] [![Discord]][discord-guild] [![Maven Central]][maven-page] [![Docs]][documentation] [![Build]][gitlab] [![Coverage]][gitlab] [![Donate]][elypia-donate]
The [Gradle]/[Maven] import strings can be found at the maven-central badge above!

## About
This is an extension for Retrofit to provide a **subjectively** cleaner interface
for wrapping webservices. Retropia primiarly adds utilities on top of the existing
libraries to control callbacks or makes common operations like bulk requests or caching
simpler.

## Getting Started
### Implementation
The only major thing that has to be done to utilize Retropia is to wrap the `Call`
objects returned by Retrofit in a `RestInterface<?>` implementation, for example:

```java
public RestAction<DefineResult> define(String term) {
    Call<DefineResult> call = service.define(term);
    return new RestAction<>(call);
}

/** 
 * If the request may return a positive result that represents the absense
 * of the result, then you can wrap it use OptionalRestAction to encourage
 * users to check if the value is present.
 */
public OptionalRestAction<DefineResult> define(String term) {
    Call<DefineResult> call = service.define(term);
    return new OptionalRestAction<>(call);
}
```

### Interface
There are two main methods to execute the request as a user:
* `complete()` - This will do a synchronous or blocking request and return an optional object.
* `queue(success, failure)` - This will do an asynchronous request, both the sucess, and failure consumers are optional.

The `failure` callback may occur on the following circumstances:
* An `IOException` occurs.
* An `HTTPException` if the HTTP status code is not 2XX.
* A `FriendlyException` occurs; this will happen when none of the above have occured,
but the wrapper didn't like something about the response, for example a 2XX status code
with the failure messages in the response.

## Structure
### Core
Core modules are shared resources that all the wrappers can pool from such as the HTTP client, or
custom deserializers and adapters. They make it easier to reuse code between wraps as well as
improve performance by managing singleton instances of special objects.

### Extension
Extensions are a way to add additional functionality to wrappers generically, so once
an extension is made it can be used by any wrapper. The best example of this is the RedisExtension
which takes a time-to-live and caches responses for the specified time. This is great for cross-application
caching and can massively save bandwidth and reduce API calls.

## Support
Should any problems occur, come visit us over on Discord! We're always around and there are
ample developers that would be willing to help; if it's a problem with the library itself then we'll
make sure to get it sorted.

[matrix-community]: https://matrix.to/#/+elypia:matrix.org "Matrix Invite"
[discord-guild]: https://discord.gg/hprGMaM "Discord Invite"
[maven-page]: https://search.maven.org/search?q=g:org.elypia.retropia "Maven Central"
[documentation]: https://elypia.gitlab.io/retropia "Documentation"
[gitlab]: https://gitlab.com/Elypia/retropia/commits/master "Repository on GitLab"
[elypia-donate]: https://elypia.org/donate "Donate to Elypia"
[Gradle]: https://gradle.org/ "Depend via Gradle"
[Maven]: https://maven.apache.org/ "Depend via Maven"

[Matrix]: https://img.shields.io/matrix/elypia-general:matrix.org?logo=matrix "Matrix Shield"
[Discord]: https://discord.com/api/guilds/184657525990359041/widget.png "Discord Shield"
[Maven Central]: https://img.shields.io/maven-central/v/org.elypia.retropia/core "Download Shield"
[Docs]: https://img.shields.io/badge/docs-retropia-blue.svg "Documentation Shield"
[Build]: https://gitlab.com/Elypia/retropia/badges/master/pipeline.svg "GitLab Build Shield"
[Coverage]: https://gitlab.com/Elypia/retropia/badges/master/coverage.svg "GitLab Coverage Shield"
[Donate]: https://img.shields.io/badge/donate-elypia-blueviolet "Donate Shield"
